package com.nachoag.exceptions

case class SparkAppException(val msg: String) extends Exception(msg)
