package com.nachoag.connection
import com.nachoag.persistence.Repository
import com.nachoag.service.spark.Session
import org.apache.spark.sql.{DataFrame, SaveMode}

case class PostgreConnection(url: String, user: String, password: String, driver: String) extends Connection {

  val FORMAT = "jdbc"
  val OPTIONS = Map(
    "url" -> url,
    "driver" -> driver,
    "user" -> user,
    "password" -> password
  )

  override def read(table: Repository): DataFrame = {
    val tableOptions = optionsFromTable(table)
    Session.getSession
      .sqlContext.read
      .format(FORMAT)
      .options(OPTIONS)
      .options(tableOptions)
      .load()
  }

  override def write(df: DataFrame, table: Repository): Unit = {
    val tableOptions = optionsFromTable(table)
    df.write
      .format(FORMAT)
      .options(OPTIONS)
      .options(tableOptions)
      .mode(SaveMode.Append)
      .save()
  }

  private def optionsFromTable(table: Repository): Map[String, String] = {
    Map("dbtable" -> table.entity.path.name)
  }

}
