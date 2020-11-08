package repository

class RepositoryProducerHiveImpl extends Repository [Any]{


//  override def readTable(table: Any): Option[Any] = Option({
//
//
//  }: Unit)

  override def writeTable(sourceTable: Any): Option[Any] = Option({

    sourceTable
      .coalesce(1)
      .write
      .mode("append")
      .option("path", "hdfs://ns-etl/warehouse/tablespace/external/hive/etl_stg.db/call_stats_iv")
      .format("parquet")
      .saveAsTable("etl_stg.call_stats_olch")


  }: Unit)

}
