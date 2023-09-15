package com.nachoag.persistence

import org.apache.spark.sql.types.StructType

trait Entity {
  val path: Path
  val schema: StructType
}