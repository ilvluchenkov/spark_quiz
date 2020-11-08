package repository

import configuration.ConfigurationPostgresDataSourceTable.{dataBaseTable, jdbcServer}
import configuration.ConfigurationSparkConfiguration.spark

class RepositoryConsumerPostgresImpl extends Repository[Any] {

  override def readTable(table: Any): Option[Any] = Option({

    var sourceTable = spark.read
      .format("jdbc")
      .option("url", jdbcServer)
      .option("dbtable", dataBaseTable)
      .option("user", "user")
      .option("password", "password")
      .option("driver", "org.postgresql.Driver")
      .load()



  }: Unit)
    .getOrElse( table.->(throw new Exception("почему что-то пошло не так")))

//  override def writeTable(table: Any): Option[Any] = Option({
//
//
//  }: Unit)
//    .getOrElse( table.->(throw new Exception("почему что-то пошло не так")))
}
