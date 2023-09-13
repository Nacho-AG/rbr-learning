package com.nachoag.constants.spark

sealed trait Master {
  val master: String
  def apply(): String = this.master
}

object Master {
  case object Local extends Master {
    override val master = "local"
  }

  case object Cluster extends Master {
    override val master = "cluster"
  }
}

