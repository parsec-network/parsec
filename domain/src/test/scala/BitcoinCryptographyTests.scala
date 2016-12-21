import org.parsec.protocol.BitcoinCryptography
import org.scalatest.{FlatSpec, Matchers}

class BitcoinCryptographyTests extends FlatSpec with Matchers with BitcoinCryptography {

  "Bitcoin addresses" should "be derived correctly from compressed public keys" in {
    val compressedPublicKey = "026591ed35e4a6c43684ede7b2cb37f6cc19fc6629a2abce8ee0f3e021e3f274e6"
    val compressedAddress = "1DipSsz4YYnjufGqVkHcbjrJEyHnxsY3Ec"

    bitcoinAddressFromPublicKey(compressedPublicKey) should be (compressedAddress)
  }
}