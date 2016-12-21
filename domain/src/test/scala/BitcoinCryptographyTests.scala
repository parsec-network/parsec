import org.parsec.protocol.BitcoinCryptography
import org.scalatest.{FlatSpec, Matchers}

class BitcoinCryptographyTests extends FlatSpec with Matchers with BitcoinCryptography {
  "Bitcoin P2PKH addresses" should "be derived correctly from public keys"

  "Bitcoin P2SH addresses" should "be derived correctly from public keys"
}