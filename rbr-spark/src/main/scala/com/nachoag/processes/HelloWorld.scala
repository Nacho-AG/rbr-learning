package com.nachoag.processes

object HelloWorld extends SparkAppProcess {
  override def execute(): Unit = {
    println("Executing \"Hello world\" process")
  }
}
