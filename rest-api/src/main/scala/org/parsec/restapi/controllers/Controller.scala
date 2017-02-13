package org.parsec
package restapi
package controllers

import akka.http.scaladsl.server.Route

import marshalling.JsonMarshalling

trait Controller extends JsonMarshalling {
  def route: Route
}
