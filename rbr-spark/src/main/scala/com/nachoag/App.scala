package com.nachoag

import com.nachoag.exceptions.SparkAppException

object App {
  
  def main(theArgs : Array[String]): Unit = {
    Arguments.parse(theArgs, Arguments) match {
      case Some(args) => execute(args)
      case None => throw SparkAppException("Error in application arguments")
    }
  }

  private def execute(args: Arguments): Unit = {
    println(s"Executing class: ${args.process}")
  }

}
