package org.parsec

/**
  * The Parsec protocol introduces a layer for `crypto-currency` transactions to take place,
  * without necessarily being registered into the the global block-chain
  */
object ParsecProtocol {

  val customerPaymentJourney =
    """
      | OoOoOoOoOoOoOoOOoO                                         OoOoOoOoOoOoOoOOoO
      | S                S                                         C                C
      | O                O                                         O                O
      | S     Seller     S  -------- 1) Create Invoice --------->  C     Client     C
      | O                O                                         O                O
      | S                S                                     ----+ sign           C
      | OoOoOoOoOoOoOoOOoO                                    /    OoOoOoOoOoOoOoOOoO
      |       ▲                                               |                   ▲
      |       |                                        2a- Signed Invoice         |
      | 4- Notify payment to seller’s client                  |            2c- Approved (balance is ok, etc.)
      |       |                                               ▼                   |
      |   /= = = = = = = = = =\                           /= = = = = = = = = =\   |
      |  ||                   ||◀-- 3- Update channel ---||                   ||  |
      |  || Parsec i.e BANK-1 ||                         || Parsec i.e BANK-2 ||--
      |  ||                   ||---  bi-directional  ---▶||                   ||
      |   \= = = = = = = = = =/                           \= = = = = = = = = =/
      |       |           ▲      \                     /      |           ▲
      |       ▼           |       \                   /       ▼           |
      |  3b - Validate Transaction \                 /  2b - Validate Transaction
      |                             \               /
      |                             |               |
      |                            5- Settle eventually
      |                             |               |
      |                             ▼               ▼
      |        /= = = = = = = = = = = = = = = = = = = = = = = = = = = = =\
      |       ||  |                      HASHED                       |  ||
      |       ||  |                    TIME LOCK                      |  ||
      |        \= = = = = = = = = = = = = = = = = = = = = = = = = = = = =/
      |                                 | | | |
      |                       ETHEREUM     or    BITCOIN or ..
    """.stripMargin

  val DEFAULT_CHANNEL = "default"
  val ALLOWED_CURRENCIES = Set("ETH", "BTC")

  /**
    * The seller needs to produce an INVOICE (through NFC or Parsec channel)
    *
    * @param invoiceAddress the sellect address for the currency to be transferred to
    * @param price          the amount of currency to exchange
    * @param currency       ETH or BTC supported
    * @param invoiceType    (optional) use to pass-in other metadata about the invoice
    */
  case class Invoice(invoiceAddress: String, price: Double, currency: String, invoiceType: String = "") {
    assert(ALLOWED_CURRENCIES.contains(currency), s"PasecProtocol: Currency $currency is not an allowed currency. Use: " + ALLOWED_CURRENCIES.mkString(" or "))

    // The Hash can be calculated as a transformation function to the invoiceAddress
    // TODO: implement me
    def produceInvoiceHash = "######-####-####"
  }

  /**
    * The gist of the Parsec Protocol lies here!
    *
    * We need to ensure that the <b>order</b> of Signed Invoices is guaranteed
    * No cluster of distributed systems can guarantee that (due to varying network latencies etc)
    *
    * So within the Parsec network - we need to preserve a chain of HashPointers that provide ordering guarantees
    */
  case class HashPointer(transactionID: String, // i.e. invoiceID
                         transactionHash: String)

  /**
    * The consumer needs to settle/pay the INVOICE
    *
    * @note step-2a
    */
  case class SignedInvoice(invoiceID: String = "auto-generate-random-UUID+channel", // import java.util.UUID
                           invoiceSignature: String,
                           supplierAddress: String, // no need to know PK
                           buyerPublicKey: String, // infer the address from here
                           currency: String,
                           price: Double,
                           channel: String = DEFAULT_CHANNEL,
                           hashPointerToPrevious: HashPointer) {
    assert(ALLOWED_CURRENCIES.contains(currency), s"PasecProtocol: Currency $currency is not an allowed currency. Use: " + ALLOWED_CURRENCIES.mkString(" or "))

    // TODO: implement me
    def getBuyerAddress: String = "generate-from-buyerPublicKey"
  }

}
