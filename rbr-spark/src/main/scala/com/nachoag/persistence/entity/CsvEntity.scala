package com.nachoag.persistence.entity

import com.nachoag.persistence.Entity
import com.nachoag.persistence.path.CsvPath

trait CsvEntity extends Entity {
  override val path: CsvPath
  val delimiter: String
  val header: Boolean
}
