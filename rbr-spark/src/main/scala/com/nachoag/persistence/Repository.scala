package com.nachoag.persistence

import org.apache.spark.sql.DataFrame

trait Repository {
  val entity: Entity
  def read: DataFrame
  def write(df: DataFrame): Unit
}
