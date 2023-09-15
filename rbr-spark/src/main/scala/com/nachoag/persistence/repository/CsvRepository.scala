package com.nachoag.persistence.repository

import com.nachoag.persistence.Repository
import com.nachoag.persistence.entity.CsvEntity

trait CsvRepository extends Repository {
  override val entity: CsvEntity
}
