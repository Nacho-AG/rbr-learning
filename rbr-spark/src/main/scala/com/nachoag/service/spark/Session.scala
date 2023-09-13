package com.nachoag.service.spark

import com.nachoag.constants.spark.Master
import com.nachoag.constants.Environment
import org.apache.spark.sql.SparkSession

object Session {

  def getSession: SparkSession = {
    SparkSession.builder()
      .master(getMaster)
      .appName("SparkApp")
      .getOrCreate()
  }

  private def getMaster: String = {
    println(System.getenv("SPARK_CONF_DIR"))
    val env = System.getenv("environment")
    if (env == Environment.Local.env) Master.Local.master
    else Master.Cluster.master
  }

}
