package com.nachoag.processes.b

import com.nachoag.processes.SparkAppProcess
import com.nachoag.service.spark.Configurator

object SessionConfiguration extends SparkAppProcess {

  override def execute(): Unit = {

    val cores = Configurator.getCores
    println(s"Number of cores: $cores")

    println(s"Default number of partitions: ${Configurator.getPartitions}")
    println(s"Default Max bytes per partition: ${Configurator.getMaxPartitionBytes}")
    println(s"Default serializer: ${Configurator.getSerializer}")

  }

}
