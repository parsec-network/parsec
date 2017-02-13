package org.parsec
package restapi
package controllers

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

import marshalling.RestApiMarshallable.Invoices._
import domain.protocol.Parsec.Invoice

class InvoicesController extends Controller {

  // TODO decide whether these are public operations...
  // TODO ...bigger deal, decide how we're going to secure certain operations... crypto signature?

  def route: Route =
    get {
      pathEndOrSingleSlash {
        complete(
          Invoice(
            invoiceAddress = "stubAddress",
            price = 1.0D,
            currency = "ETH",
            invoiceType = "stubInvoice"
          )
        )
      }
    }

}
