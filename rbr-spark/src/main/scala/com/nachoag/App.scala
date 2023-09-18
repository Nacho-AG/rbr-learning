package com.nachoag

import com.nachoag.exceptions.SparkAppException
import org.apache.log4j.{Level, LogManager}

object App {
  
  def main(theArgs : Array[String]): Unit = {
    Arguments.parse(theArgs, Arguments()) match {
      case Some(args) => execute(args)
      case None => throw SparkAppException("Error in application arguments")
    }
  }

  private def execute(args: Arguments): Unit = {
    configureLogs()
    Orchestrator.launch(args.process)
  }

  private def configureLogs(): Unit = {
    val logManager = LogManager.getRootLogger
    logManager.setLevel(Level.WARN)
  }

}
