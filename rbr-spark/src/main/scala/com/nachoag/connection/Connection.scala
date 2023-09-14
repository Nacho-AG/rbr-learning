package com.nachoag.connection

import com.nachoag.persistence.Repository
import org.apache.spark.sql.DataFrame

trait Connection {
  val FORMAT: String
  val OPTIONS: Map[String, String]
  def read(table: Repository): DataFrame
  def write(df: DataFrame, table: Repository): Unit
}
