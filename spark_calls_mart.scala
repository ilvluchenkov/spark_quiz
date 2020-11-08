import org.apache.spark.sql.SparkSession
//import org.apache.spark.sql.functions.{avg, sum, max, min}
import org.apache.spark.sql.functions._

object spark_calls_mart extends App {

  val spark = SparkSession
    .builder
    .appName( name = "spark_calls_mart")
    .enableHiveSupport()
    .getOrCreate()

  val sourceTable = spark.read
    .format("jdbc")
    .option("url", "jdbc:postgresql://10.31.153.191:30134/demo_base")
    .option("dbtable", "demo_base.public.call_stats")
    .option("user", "spark_demo")
    .option("password", "jw8s0F4")
    .option("driver", "org.postgresql.Driver")
    .load()

  sourceTable
    .coalesce(1)
    .write
    .mode("append")
    .option("path", "hdfs://ns-etl/warehouse/tablespace/external/hive/etl_stg.db/call_stats_iv")
    .format("parquet")
    .saveAsTable("etl_stg.call_stats_iv")

  val datamartDf = spark
    .table("etl_stg.call_stats_iv")
    .select("state", "total_day_calls", "total_day_minutes")

  datamartDf.groupBy("state")
    .agg(
      min("total_day_calls").as("min_total_day_calls"),
		  max("total_day_calls").as("max_total_day_calls"),
		  avg("total_day_calls").as("avg_total_day_calls"),
		  sum("total_day_minutes").as("sum_total_day_calls_minutes"))
		  .show(false)
      .coalesce(1)
      .write
      .mode("append")
      .option("path", "hdfs://ns-etl/warehouse/tablespace/external/hive/etl_stg.db/call_stats_mart_iv")
      .format("parquet")
  .saveAsTable("etl_stg.call_stats_mart_iv")

}


 




