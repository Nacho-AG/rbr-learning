package com.nachoag.processes.a

import com.nachoag.processes.SparkAppProcess

object HelloWorld extends SparkAppProcess {
  override def execute(): Unit = {
    println("Executing \"Hello world\" process")
  }
}
