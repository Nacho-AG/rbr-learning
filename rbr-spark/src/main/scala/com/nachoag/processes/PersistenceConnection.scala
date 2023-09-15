package com.nachoag.processes

import com.nachoag.persistence._
import com.nachoag.persistence.field.{Field, FieldMetadata}
import com.nachoag.persistence.path.DatabasePath
import com.nachoag.service.spark.DataFrameCreator
import org.apache.spark.sql.types.{LongType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row}

object PersistenceConnection extends SparkAppProcess {

  object ExampleEntity extends Entity {

    final val ID = Field("ExampleId")

    override val path: Path = DatabasePath("exampleName", "exampleDB", "exampleSchema")
    override val schema: StructType = StructType(Seq(
      StructField(ID.name, LongType, nullable = false, metadata = FieldMetadata(isPk = true)),
    ))
  }

  object ExampleRepository extends Repository {
    override val entity: Entity = ExampleEntity
    override def read: DataFrame = {
      println(s"Reading from ${entity.path}")
      val rows = (1L to 20L) map (id => Row(id))
      DataFrameCreator.create(entity.schema, rows)
    }
    override def write(df: DataFrame): Unit = {
      println(s"Writing to ${entity.path}")
      df.show()
    }
  }

  override def execute(): Unit = {
    val df = ExampleRepository.read
      .filter(ExampleEntity.ID.column <= 10)
    ExampleRepository.write(df)
  }

}
