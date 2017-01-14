package org.parsec
package domain
package crypto

sealed trait Currency extends EnumValue
object Currency extends Enum[Currency] {

  val values = findValues

  case object ETH extends Currency
  case object BTC extends Currency

}
