package com.nachoag.persistence

import com.nachoag.persistence.path.{DatabasePath, Path}
import org.apache.spark.sql.types.StructType

trait Entity {
  val path: Path
  val schema: StructType
}