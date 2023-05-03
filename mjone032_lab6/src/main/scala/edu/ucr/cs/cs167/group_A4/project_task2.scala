package edu.ucr.cs.cs167.group_A4

import org.apache.spark.SparkConf
import org.apache.spark.beast.SparkSQLRegistration
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object project_task2 {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Beast Example")
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")
    val spark: SparkSession.Builder = SparkSession.builder().config(conf)

    val sparkSession: SparkSession = spark.getOrCreate()
    val sparkContext = sparkSession.sparkContext
    SparkSQLRegistration.registerUDT
    SparkSQLRegistration.registerUDF(sparkSession)
    val operation: String = "choropleth-map"
    try {
      import edu.ucr.cs.bdlab.beast._
      val t1 = System.nanoTime()
      var validOperation = true
      val tweetsDF = sparkSession.read.format("csv")
        .option("sep", "\t")
        .option("inferSchema", "true")
        .option("header", "true")
        .load("Chicago_Crimes_ZIP.parquet")

      operation match {
        case "choropleth-map" =>
          val outputFile: String = args(0)
          sparkSession.read.parquet("Chicago_Crimes_ZIP.parquet")
            .createOrReplaceTempView("crimes")
          sparkSession.sql (
            s"""
                      SELECT crimes.ZIPCode, count(*) as count
                      FROM crimes
                      GROUP BY crimes.ZIPCode
                      """)
            .createOrReplaceTempView ("keyword_counts")
          sparkContext.shapefile("tl_2018_us_zcta510.zip")
            .toDataFrame(sparkSession)
            .createOrReplaceTempView("counties")
          sparkSession.sql(
            s"""
                      SELECT counties.*, keyword_counts.count
                      FROM counties JOIN keyword_counts
                      ON counties.ZCTA5CE10 = keyword_counts.ZIPCode
                    """.stripMargin)
            .toSpatialRDD
            .coalesce(1)
            .saveAsShapefile(outputFile)
        case _ => validOperation = false
      }
      val t2 = System.nanoTime()
      if (validOperation)
        println(s"Operation '$operation' took ${(t2 - t1) * 1E-9} seconds")
      else
        Console.err.println(s"Invalid operation '$operation'")
    } finally {
      sparkSession.stop()
    }
  }
}
