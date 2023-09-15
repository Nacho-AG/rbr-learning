package com.nachoag.persistence.path

import com.nachoag.persistence.Path

case class CsvPath(name: String, dir: String = "/") extends Path {
  override def toString: String = s"$dir/$name.csv"
}

case object CsvPath {
   def apply: String = super.toString
}
