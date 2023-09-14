package com.nachoag.persistence.field

import org.apache.spark.sql.Column
import org.apache.spark.sql.functions.col

case class Field(name: String) {
  def column: Column = col(name)
}
