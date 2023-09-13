package com.nachoag.processes

import com.nachoag.service.spark.Configurator

object SessionConfiguration extends SparkAppProcess {

  override def execute(): Unit = {
    val cores = Configurator.getCores
    println(s"Number of cores: $cores")
  }

}
