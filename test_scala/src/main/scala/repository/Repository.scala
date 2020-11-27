package repository

trait Repository[Any] {


  def readTable(table: Any): Option[Any]
  def writeTable(table: Any): Option[Any]


}
