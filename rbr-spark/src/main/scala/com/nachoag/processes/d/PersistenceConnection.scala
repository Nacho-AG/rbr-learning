package com.nachoag.processes.d

import com.nachoag.domain.file.{InputExampleRepository, OutputExampleRepository}
import com.nachoag.processes.SparkAppProcess

object PersistenceConnection extends SparkAppProcess {

  override def execute(): Unit = {
    val df = InputExampleRepository.read
    df.show()
    OutputExampleRepository.write(df)
  }

}
