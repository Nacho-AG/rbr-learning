package com.nachoag.connection

import com.nachoag.persistence.Repository
import com.nachoag.service.spark.Session
import org.apache.spark.sql.{DataFrame, SaveMode}

case class PostgreConnection(url: String, user: String, password: String, driver: String)  {

  val FORMAT = "jdbc"
  val OPTIONS = Map(
    "url" -> url,
    "driver" -> driver,
    "user" -> user,
    "password" -> password
  )

  def read(repository: Repository): DataFrame = {
    val tableOptions = optionsFromTable(repository)
    Session.getSession
      .sqlContext.read
      .format(FORMAT)
      .options(OPTIONS)
      .options(tableOptions)
      .load()
  }

  def write(df: DataFrame, repository: Repository): Unit = {
    val tableOptions = optionsFromTable(repository)
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
