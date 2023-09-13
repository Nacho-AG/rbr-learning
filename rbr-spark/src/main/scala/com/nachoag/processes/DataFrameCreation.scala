package com.nachoag.processes

import com.nachoag.service.spark.DataFrameCreator
import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, Row}

import scala.util.Random

object DataFrameCreation extends SparkAppProcess {

  override def execute(): Unit = {
    val df = createRandomDataFrame()
    df.show(numRows = df.count.toInt, truncate = false)
    df.show(numRows = 1, truncate = 140, vertical = true)
  }

  private def createRandomDataFrame(): DataFrame = {
    val schema = StructType(Seq(
      StructField("Id", LongType, nullable = false),
      StructField("Description", StringType, nullable = true),
      StructField("Value", DecimalType(10, 9), nullable = false),
    ))
    val descriptions = Seq("An example row", "Another example of row", "...", "", null)
    val random = new Random()
    val rows = (1L to 100L) map { id => {
        val description = descriptions(random.nextInt(descriptions.length))
        val value = BigDecimal.valueOf(random.nextDouble())
        Row(id, description, value)
      }
    }
    DataFrameCreator.create(schema, rows)
  }

}
