package com.nachoag

import com.nachoag.exceptions.SparkAppException

object Orchestrator {

  def launch(process: String): Unit = {
    val thePath = s"com.nachoag.processes.$process"
    try {
      val theClass = Class.forName(thePath)
      val theMethod = theClass.getMethod("execute")
      theMethod.invoke(theClass)
    } catch {
      case e: ClassNotFoundException => throw SparkAppException("Process not found")
      case e: NoSuchMethodException => throw SparkAppException("Process not implement yet")
      case e: Throwable => throw e
    }
  }

}
