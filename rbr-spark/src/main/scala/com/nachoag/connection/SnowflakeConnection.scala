package com.nachoag.connection

import com.nachoag.persistence.Repository
import com.nachoag.service.spark.Session
import net.snowflake.client.jdbc.SnowflakeSQLException
import org.apache.spark.sql.{DataFrame, SaveMode}

case class SnowflakeConnection(url: String, user: String, password: String, warehouse: String, tag: String) extends Connection {

  private object ErrorCodes {
    val NOT_EXIST_OR_NOT_AUTHORIZED = 2003
    val INSUFFICIENT_PRIVILEDGES = 3001
  }

  val FORMAT = "net.snowflake.spark.snowflake"
  val OPTIONS = Map(
    "sfURL" -> url,
    "sfUser" -> user,
    "sfPassword" -> password,
    "sfWarehouse" -> warehouse,
    "preactions" -> s"alter session set query_tag='$tag'",
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
    def handle(e: SnowflakeSQLException): Unit = e.getErrorCode match {
      case ErrorCodes.NOT_EXIST_OR_NOT_AUTHORIZED => throw e
      case ErrorCodes.INSUFFICIENT_PRIVILEDGES => throw e
      case _ => throw e
    }

    val tableOptions = optionsFromTable(table)
    try {
      df.write
        .format(FORMAT)
        .options(OPTIONS)
        .options(tableOptions)
        .option("continue_on_error", "off")
        .option("column_mapping", "name")
        .option("column_mismatch_behavior", "ignore")
        .option("internal_skip_write-when_writing-empty-dataframe", "true")
        .mode(SaveMode.Append)
        .save()
    } catch {
      case e: SnowflakeSQLException => handle(e)
      case e: Throwable => throw e
    }
  }

  private def optionsFromTable(table: Repository): Map[String, String] = {
    val (db, schema, name) = table.entity.path.name.split(".")
    Map("sfDatabase" -> db, "sfSchema" -> schema, "dbtable" -> name)
  }
}
