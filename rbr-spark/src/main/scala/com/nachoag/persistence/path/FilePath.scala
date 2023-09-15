package com.nachoag.persistence.path

import com.nachoag.persistence.Path

case class FilePath(name: String, dir: String = "/") extends Path {
  override def toString: String = s"$dir/$name"
}

case object FilePath {
   def apply: String = super.toString
}
