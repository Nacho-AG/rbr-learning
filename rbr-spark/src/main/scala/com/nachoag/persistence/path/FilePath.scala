package com.nachoag.persistence.path

case class FilePath(name: String, dir: String = null) extends Path {
  override def toString: String = (dir) match {
    case ("") => s"$name"
    case (null) => s"$name"
    case (_) => s"$dir.$name"
  }
}

case object FilePath {
   def apply: String = super.toString
}
