package configuration

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, sum, max, min}

import ConfigurationSparkConfiguration.spark




object ConfigurationPostgresDataSourceTable {


  protected val jdbcServer: String = System.getProperty("url")
  protected val dataBaseTable: String = System.getProperty("dbtable")
  protected val user: String = System.getProperty("url")
  protected val password: String = System.getProperty("url")


}
