package com.nachoag.connection

import com.nachoag.persistence.repository.CsvRepository
import com.nachoag.service.spark.Session
import org.apache.spark.sql.DataFrame

case class CsvConnection() {

  val FORMAT = "csv"

  def read(repository: CsvRepository): DataFrame = {
    val fileOptions = optionsFromTable(repository)
    Session.getSession
      .sqlContext
      .read
      .options(fileOptions)
      .load(repository.entity.path.toString)

  }

  def write(df: DataFrame, repository: CsvRepository): Unit = {
  }

  private def optionsFromTable(repository: CsvRepository): Map[String, String] = {
    Map(
      "delimiter" -> repository.entity.delimiter,
      "header" -> repository.entity.header.toString
    )
  }
}
