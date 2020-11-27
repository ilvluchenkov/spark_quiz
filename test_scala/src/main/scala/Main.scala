import scala.collection.JavaConverters._

object Main {

  def main(args: Array[String]): Unit = {


//    EnvironmentVariables.environmentVars
//    EnvironmentVariables.properties


//    print(System.getProperty("server"))
  }


}




object EnvironmentVariables {

  val environmentVars = System.getenv().asScala
  for ((k,v) <- environmentVars) println(s"key: $k, value: $v")

  val properties = System.getProperties().asScala
  for ((k,v) <- properties) println(s"key: $k, value: $v")

}




