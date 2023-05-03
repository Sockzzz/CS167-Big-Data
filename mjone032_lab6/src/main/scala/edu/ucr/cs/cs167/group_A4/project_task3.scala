package edu.ucr.cs.cs167.group_A4

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession}

object project_task3 {

  def main(args: Array[String]) {
    val conf = new SparkConf
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")
    println(s"Using Spark master '${conf.get("spark.master")}'")

    val spark = SparkSession
      .builder()
      .appName("ProjectA_task3_AppSQL")
      .config(conf)
      .getOrCreate()

    try {
      //inputFile.printSchema()

      import spark
      .implicits._


      //counts the total number of records in the file
      /*
      var query: String = "select count(*) from myTable"
      val count: Long = spark.sql(query).first().getAs[Long](0)

      println(s"Total count for file is $count")

       */

      //prints out all the years
      /*
      query = "select Year from myTable order by Year"
      spark.sql(query).foreach(row => println(s"${row.get(0)}"))
      */

      //selects all the dates as timestamps
      //query = "select to_timestamp(Date, 'MM/dd/yyy hh:mm:ss a') from myTable"

      //prints out the primarytype and timestamp of each crime if its between the given time
      //query = "select PrimaryType, count(*) as count from myTable where to_timestamp(Date, 'MM/dd/yyy hh:mm:ss a') > to_date('01/01/2000','MM/dd/yyyy') and to_timestamp(Date, 'MM/dd/yyy hh:mm:ss a') < to_date('01/01/2018','MM/dd/yyyy') group by PrimaryType"


      //inputFile is a dataframe here
      val inputFile = spark.read.parquet("output_task1.parquet")
      val startDate: String = args(0)
      val endDate: String = args(1)

      inputFile.createOrReplaceTempView("myTable")

      var query: String = ""


      //query for primarytype and count given inputted times
      query = s"select PrimaryType, count(*) as count from myTable where to_timestamp(Date, 'MM/dd/yyy hh:mm:ss a') > to_date('$startDate','MM/dd/yyyy') and to_timestamp(Date, 'MM/dd/yyy hh:mm:ss a') < to_date('$endDate','MM/dd/yyyy') group by PrimaryType"

      spark.sql(query).foreach(row => println(s"${row.get(0)}, ${row.get(1)}"))

      //just need to write the query to a file
      spark.sql(query).write.format("csv").save("output1.csv")



    } finally {
      spark.stop
    }
  }
}