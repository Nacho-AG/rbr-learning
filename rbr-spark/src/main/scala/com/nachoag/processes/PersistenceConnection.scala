package com.nachoag.processes

import com.nachoag.domain.file.{InputExampleRepository, OutputExampleRepository}

object PersistenceConnection extends SparkAppProcess {

  override def execute(): Unit = {
    val df = InputExampleRepository.read
    df.show()
    OutputExampleRepository.write(df)
  }

}
