package com.nachoag.persistence.entity

import com.nachoag.persistence.Entity
import com.nachoag.persistence.path.FilePath

trait CsvEntity extends Entity {
  override val path: FilePath
  val delimiter: String
  val header: Boolean
}
