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

  def getPartitions: Long = getSparkConf("spark.sql.shuffle.partitions").toLong

  def getMaxPartitionBytes: Long = getSparkConf("spark.sql.files.maxPartitionBytes").toLong

  def getMaxBytesInDriver: Long = getSparkConf("spark.driver.maxResultSize").toLong

  def getSerializer: String = getSparkConf("spark.serializer")

  private def getSparkConf(property: String): String = Session.getSession.sparkContext.getConf.get(property)
  
}
