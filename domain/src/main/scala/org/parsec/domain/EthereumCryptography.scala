package org.parsec
package domain

import java.util

import org.apache.commons.codec.binary.Hex
import org.bouncycastle.jcajce.provider.digest.Keccak

trait EthereumCryptography {
  def ethereumAddressFromPublicKey(key: String): String = {
    val keyBytes = Hex.decodeHex(key.toCharArray) // Convert HEX public key to bytes
    val keccak256 = new Keccak.Digest256
    val keyHash = keccak256.digest(keyBytes) // Take the Keccak-256 hash of the public key
    val addressBytes = util.Arrays.copyOfRange(keyHash, 12, keyHash.length) // Drop the 12 first bytes of the hash
    Hex.encodeHexString(addressBytes) // Return the hex hash
  }
}
