package org.parsec

import enumeratum.EnumMacros

trait EnumValue extends Product with Serializable {
  def id: String = toString
}

trait Enum[A <: EnumValue] {
  def values: Seq[A]
  def findValues: Seq[A] = macro EnumMacros.findValuesImpl[A]
}