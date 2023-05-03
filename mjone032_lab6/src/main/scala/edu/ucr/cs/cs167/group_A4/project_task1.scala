package edu.ucr.cs.cs167.group_A4

import edu.ucr.cs.bdlab.beast.geolite.{Feature, IFeature}
import org.apache.spark.SparkConf
import org.apache.spark.beast.SparkSQLRegistration
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object project_task1 {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Beast Example")
    // Set Spark master to local if not already set
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")

    val spark: SparkSession.Builder = SparkSession.builder().config(conf)

    val sparkSession: SparkSession = spark.getOrCreate()
    val sparkContext = sparkSession.sparkContext
    SparkSQLRegistration.registerUDT
    SparkSQLRegistration.registerUDF(sparkSession)

    val inputFile: String = args(0)
    val outputFile: String = args(1)
    try {

      import edu.ucr.cs.bdlab.beast._

      val chicagoCrimeDF = sparkSession.read.format("csv")
        .option("sep", ",")
        .option("inferSchema", "true")
        .option("header", "true")
        .load(inputFile)

      val chicagoRDD: SpatialRDD = sparkContext.readCSVPoint(inputFile, "x", "y", ',') //tweetsDF.selectExpr("*", "ST_CreatePoint(x, y) AS geometry").toSpatialRDD
      val chicagoZipcodesDF = sparkSession.read.format("shapefile").load("tl_2018_us_zcta510.zip")
      val chicagoZipcodesRDD: SpatialRDD = chicagoZipcodesDF.toSpatialRDD
      val crimeZipcodesRDD: RDD[(IFeature, IFeature)] = chicagoRDD.spatialJoin(chicagoZipcodesRDD)
      val crimeZipcodes: DataFrame = crimeZipcodesRDD.map({ case (chicagoCrime, zipcode) => Feature.append(chicagoCrime, zipcode.getAs[String]("ZCTA5CE10"), "ZIPCode") })
        .toDataFrame(sparkSession).drop("g")

      val convertedDF: DataFrame = crimeZipcodes.withColumnRenamed("Case Number", "CaseNumber")
        .withColumnRenamed("Primary Type", "PrimaryType")
        .withColumnRenamed("Location Description", "LocationDescription")
        .withColumnRenamed("Community Area", "CommunityArea")
        .withColumnRenamed("FBI Code", "FBICode")
        .withColumnRenamed("X Coordinate", "XCoordinate")
        .withColumnRenamed("Y Coordinate", "YCoordinate")
        .withColumnRenamed("Updated On", "UpdatedOn")

      convertedDF.printSchema()
      convertedDF.show()
      convertedDF.repartition(1).write.mode(SaveMode.Overwrite).parquet(outputFile)

    } finally {
      sparkSession.stop()
    }
  }
}