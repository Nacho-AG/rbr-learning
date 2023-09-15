package com.nachoag.domain.file

import com.nachoag.connection.CsvConnection
import com.nachoag.persistence.entity.CsvEntity
import com.nachoag.persistence.repository.CsvRepository
import org.apache.spark.sql.DataFrame

object OutputExampleRepository extends CsvRepository {
  override val entity: CsvEntity = OutputExampleEntity

  override def read: DataFrame = {
    CsvConnection().read(this)
  }

  override def write(df: DataFrame): Unit = {
    CsvConnection().write(df, this)
  }
}
