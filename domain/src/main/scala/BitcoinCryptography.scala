package org.parsec.protocol

import org.apache.commons.codec.binary.Hex
import org.bitcoinj.core.{ECKey, NetworkParameters}

trait BitcoinCryptography {
  // TODO: implement me
  def bitcoinP2PKHAddressFromPublicKey(key: String): String = {
    "btc-public-key"
  }

  // TODO: implement me
  def bitcoinP2SHAddressFromPublicKey(key: String): String = {
    "btc-public-key"
  }

  def bitcoinAddressFromPublicKey(key: String): String = {
    val ECkey = ECKey.fromPublicOnly(Hex.decodeHex(key.toCharArray))
    val netParams = NetworkParameters.prodNet() // Generating only production addresses
    val addressFromKey = ECkey.toAddress(netParams)
    addressFromKey.toString
  }
}