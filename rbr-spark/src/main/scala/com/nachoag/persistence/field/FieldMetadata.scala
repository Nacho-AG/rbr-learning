package com.nachoag.persistence.field

import org.apache.spark.sql.types.{Metadata, MetadataBuilder}

case class FieldMetadata(val isPk: Boolean)
case object FieldMetadata {
  private val isPkMetadata: String = "IsPk"
  def apply(isPk: Boolean): Metadata = new MetadataBuilder().putBoolean(isPkMetadata, isPk).build()
  def isPk(metadata: Metadata): Boolean = metadata.getBoolean(isPkMetadata)
}
