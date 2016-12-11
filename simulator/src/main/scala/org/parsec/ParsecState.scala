package org.parsec

import org.parsec.ParsecProtocol._

/**
  * For each address - keep the balance and history of recent
  * [ rolling window - 5 min ] + [ most recent transaction]
  *
  * @note compacted Transactions, to overcome soft-guarantee of
  *       ordering of topic
  * @note this is step-2b
  */
object ParsecState {

  // mock the state ( mocked DB )
  val DB = List(
    AddressBalance("address1", 0.011),
    AddressBalance("address2", 0.004)
  )

  // controller
  def submitTransaction(signedInvoice: SignedInvoice): Either[Boolean, String] = {
    assert(validateTransaction(), "Invalid transaction")
    //Right("Transaction rejected for reason : X")
    Left(true)
  }

  // TODO
  private def validateTransaction(): Boolean = true

}

// This class is used only internally in this file
case class AddressBalance(address: String, balance: Double)
