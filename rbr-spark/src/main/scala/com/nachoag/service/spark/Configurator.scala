package com.nachoag.service.spark

object Configurator {

  def getCores: Int = {
    val spark = Session.getSession
    val context = spark.sparkContext
    val numExecutors = context.statusTracker.getExecutorInfos.length
    val numWorkers = numExecutors - 1
    val numProcessors = java.lang.Runtime.getRuntime.availableProcessors

    (numWorkers * numProcessors).max(numProcessors)
  }
}
