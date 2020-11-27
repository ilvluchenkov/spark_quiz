package configuration

object ConfigurationSparkConfiguration {

  private val sparkCallsMart = System.getProperty("spark_calls_mart")
  val spark = SparkSession.builder
    .appName( name = sparkCallsMart)
    .enableHiveSupport()
    .getOrCreate()

  //getter->result.
  getOrElse(throw new Exception("все плохо по тому"))

}
