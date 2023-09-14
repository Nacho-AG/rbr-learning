package com.nachoag.persistence.path

case class DatabasePath(name: String, db: String = null, schema: String = null) extends Path {
  override def toString: String = (db, schema) match {
    case ("", _) => s"$schema.$name"
    case (null, _) => s"$schema.$name"
    case ("", "") => s"$name"
    case (null, null) => s"$name"
    case (_, _) => s"$db.$schema.$name"
  }
}

case object DatabasePath {
   def apply: String = super.toString
}
