package service

import configuration.ConfigurationSparkConfiguration.spark

class ServiceSparkQuiz extends DataMartDfService {



  val datamartDf = spark
    .table("etl_stg.call_stats_iv")
    .select("state", "total_day_calls", "total_day_minutes")


  private val value = System.getProperty("hive_path")

  override def aggregateData(table: Any): Option[Any] = Option({


    datamartDf.groupBy("state")
      .agg(
        min("total_day_calls").as("min_calls"),
        max("total_day_calls").as("max_calls"),
        avg("total_day_calls").as("avg_calls"),
        sum("total_day_minutes").as("sum_minutes"))
      .coalesce(1)
      .write
      .mode("append")
      .option("path", value)
      .format("parquet")
      .saveAsTable("etl_stg.call_stats_mart_iv")


  }: Unit)

}
