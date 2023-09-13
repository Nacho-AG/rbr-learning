package com.nachoag.constants

sealed trait Environment {
  val env: String
  def apply(): String = this.env
}

object Environment {
  case object Local extends Environment {
    override val env = "local"
  }
}
