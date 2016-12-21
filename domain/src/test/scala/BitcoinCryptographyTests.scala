import org.parsec.protocol.BitcoinCryptography
import org.scalatest.{FlatSpec, Matchers}

class BitcoinCryptographyTests extends FlatSpec with Matchers with BitcoinCryptography {

  "Bitcoin addresses" should "be derived correctly from compressed public keys" in {
    val compressedPublicKey1 = "026591ed35e4a6c43684ede7b2cb37f6cc19fc6629a2abce8ee0f3e021e3f274e6"
    val address1 = "1DipSsz4YYnjufGqVkHcbjrJEyHnxsY3Ec"
    val compressedPublicKey2 = "030589ee559348bd6a7325994f9c8eff12bd5d73cc683142bd0dd1a17abc99b0dc"
    val address2 = "1KbUJ4x8epz6QqxkmZbTc4f79JbWWz6g37"

    bitcoinAddressFromPublicKey(compressedPublicKey1) should be (address1)
    bitcoinAddressFromPublicKey(compressedPublicKey2) should be (address2)
  }

  "Bitcoin addresses" should "be derived correctly from uncompressed public keys" in {
    val unCompressedPublicKey = "046591ed35e4a6c43684ede7b2cb37f6cc19fc6629a2abce8ee0f3e021e3f274e6013ba122194117982eb818322f3e3b12a7edb15d586cdff87714de281c4ce5ec"
    val address = "19ZE9JY8NjX569nXGeHKwHzuqmaVvu3WuT"

    bitcoinAddressFromPublicKey(unCompressedPublicKey) should be (address)
  }
}