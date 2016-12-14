import org.parsec.protocol.Parsec
import org.scalatest._

class ParsecTests extends FlatSpec with Matchers {

  "Ethereum addresses" should "be derived correctly from public keys" in {
    val publicKey = "bb48ae3726c5737344a54b3463fec499cb108a7d11ba137ba3c7d043bd6d7e14994f60462a3f91550749bb2ae5411f22b7f9bee79956a463c308ad508f3557df"
    val address = "89b44e4d3c81ede05d0f5de8d1a68f754d73d997"

    Parsec.ethereumAddressFromPublicKey(publicKey) should be (address)
  }

  "Bitcoin P2PKH addresses" should "be derived correctly from public keys" in {
    // WARNING, this example publicKey seems to be too long
    val publicKey = "0450863AD64A87AE8A2FE83C1AF1A8403CB53F53E486D8511DAD8A04887E5B23522CD470243453A299FA9E77237716103ABC11A1DF38855ED6F2EE187E9C582BA6"
    val address = "16UwLL9Risc3QfPqBUvKofHmBQ7wMtjvM"

    Parsec.bitcoinP2PKHAddressFromPublicKey(publicKey) should be (address)
  }

  "Bitcoin P2SH addresses" should "be derived correctly from public keys"
}