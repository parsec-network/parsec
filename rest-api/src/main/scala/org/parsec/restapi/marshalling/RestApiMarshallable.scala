package org.parsec
package restapi
package marshalling

import domain.protocol.Parsec.Invoice

/**
  * Simple typeclass to retoactively extend the type properties of domain classes so that they can be un/marshalled
  * by [[org.parsec.restapi.marshalling.JsonMarshalling]].
  *
  * This means we can constrain the marshallable types (i.e. by not just allowing `Any`), whilst keeping
  * [[org.parsec.domain]] independent of [[org.parsec.restapi]].
  *
  * @tparam A
  */
trait RestApiMarshallable[A]

object RestApiMarshallable {
  object Invoices {
    implicit object InvoiceMarshallable extends RestApiMarshallable[Invoice]
  }
}