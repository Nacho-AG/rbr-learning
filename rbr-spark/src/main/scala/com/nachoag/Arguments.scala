package com.nachoag

import scopt.OptionParser

case class Arguments(process: String = "Default")

object Arguments extends OptionParser[Arguments]("Arguments") {
  head("SparkApp", "1.0.0")
  opt[String]('p', "process")
    .required()
    .valueName("<process>")
    .action((value, args) => args.copy(process = value))
    .text("Process is the source path of the class to execute within the application")
}