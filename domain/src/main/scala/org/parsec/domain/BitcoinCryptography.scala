package org.parsec
package domain

import org.apache.commons.codec.binary.Hex
import org.bitcoinj.core.{ECKey, NetworkParameters}

trait BitcoinCryptography {
  def bitcoinAddressFromPublicKey(key: String): String = {
    val ECkey = ECKey.fromPublicOnly(Hex.decodeHex(key.toCharArray))
    val netParams = NetworkParameters.prodNet() // Generating only production addresses
    val addressFromKey = ECkey.toAddress(netParams)
    addressFromKey.toString
  }
}