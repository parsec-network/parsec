import org.parsec.protocol.EthereumCryptography
import org.scalatest.{FlatSpec, Matchers}

class EthereumCryptographyTests extends FlatSpec with Matchers with EthereumCryptography {
  "Ethereum addresses" should "be derived correctly from public keys" in {
    val publicKey = "bb48ae3726c5737344a54b3463fec499cb108a7d11ba137ba3c7d043bd6d7e14994f60462a3f91550749bb2ae5411f22b7f9bee79956a463c308ad508f3557df"
    val address = "89b44e4d3c81ede05d0f5de8d1a68f754d73d997"

    ethereumAddressFromPublicKey(publicKey) should be (address)
  }
}
