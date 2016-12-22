import org.parsec.protocol.Parsec
import org.scalatest._

class ParsecTests extends FlatSpec with Matchers {
  "The invoice hash" should "be a hash of invoiceID + supplierAddress + buyerAddress + currency + price" in {
    val invoiceID = "auto-generated-random-UUID+channel" // ToDo: Autogenerate id
    val previousInvoiceId = "previousInvoiceId"
    val previousTransactionHash = "previousTransactionHash"
    val invoiceSignature = "signature1" // ToDo: validate signature
    val supplierAddress = "89b44e4d3c81ede05d0f5de8d1a68f754d73d998"
    val buyerAddress = "89b44e4d3c81ede05d0f5de8d1a68f754d73d997"
    val buyerPublicKey = "bb48ae3726c5737344a54b3463fec499cb108a7d11ba137ba3c7d043bd6d7e14994f60462a3f91550749bb2ae5411f22b7f9bee79956a463c308ad508f3557df"
    val currency = "ETH"
    val price = 1.15
    val hashPointerToPrevious = Parsec.HashPointer(previousInvoiceId,previousTransactionHash)

    Parsec.SignedInvoice( invoiceID,
                          invoiceSignature,
                          supplierAddress,
                          buyerPublicKey,
                          buyerAddress,
                          currency,
                          price,
                          Parsec.DEFAULT_CHANNEL,
                          hashPointerToPrevious).invoiceHash should be ("")
  }
}