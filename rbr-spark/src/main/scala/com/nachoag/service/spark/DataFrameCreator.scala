package com.nachoag.service.spark

import org.apache.spark.sql.{DataFrame, Row}
import org.apache.spark.sql.types.StructType

object DataFrameCreator {

  def create(schema: StructType, rows: Seq[Row]): DataFrame = {
    val spark = Session.getSession
    spark.createDataFrame(spark.sparkContext.parallelize(rows), schema)
  }

  def createEmpty(schema: StructType): DataFrame = {
    create(schema, Seq[Row]())
  }

}
