package com.nachoag.domain.file

import com.nachoag.persistence.entity.CsvEntity
import com.nachoag.persistence.field.{Field, FieldMetadata}
import com.nachoag.persistence.path.CsvPath
import org.apache.spark.sql.types.{LongType, StructField, StructType, VarcharType}

object OutputExampleEntity extends CsvEntity {

  final val ID = Field("ExampleId")
  final val DESC = Field("ExampleDescription")
  final val VAL = Field("ExampleValue")

  override val path: CsvPath = CsvPath("OUTPUT_EXAMPLE_DATA", "src/main/resources")
  override val delimiter: String = ","
  override val header: Boolean = true

  override val schema: StructType = StructType(Seq(
    StructField(ID.name, LongType, nullable = false, metadata = FieldMetadata(isPk = true)),
    StructField(DESC.name, VarcharType(50), nullable = false, metadata = FieldMetadata(isPk = true)),
    StructField(VAL.name, LongType, nullable = false, metadata = FieldMetadata(isPk = true)),
  ))

}