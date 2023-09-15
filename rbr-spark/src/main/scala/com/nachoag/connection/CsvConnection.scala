package com.nachoag.connection

import com.nachoag.persistence.repository.CsvRepository
import com.nachoag.service.spark.Session
import org.apache.spark.sql.{DataFrame, SaveMode}

case class CsvConnection() {

  val FORMAT = "csv"

  def read(repository: CsvRepository): DataFrame = {
    val fileOptions = optionsFromTable(repository)
    Session.getSession
      .sqlContext.read
      .format(FORMAT)
      .options(fileOptions)
      .load(repository.entity.path.toString)
  }

  def write(df: DataFrame, repository: CsvRepository): Unit = {
    val fileOptions = optionsFromTable(repository)
    df.write
      .format(FORMAT)
      .options(fileOptions)
      .mode(SaveMode.Overwrite) // Overwrite the file if it already exists
      .save(repository.entity.path.toString)
  }

  private def optionsFromTable(repository: CsvRepository): Map[String, String] = {
    Map(
      "delimiter" -> repository.entity.delimiter,
      "header" -> repository.entity.header.toString
    )
  }
}
