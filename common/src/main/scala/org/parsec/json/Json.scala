package org.parsec
package json

import cats.data.Validated
import Validated._
import cats.syntax.either._
import org.json4s._
import org.json4s.{Formats, Serialization}
import org.json4s.JsonAST.JValue
import org.json4s.native.{JsonMethods, JsonParser, Printer}

object Json {

  sealed trait Exception extends ParsecException
  case class ParseException(message: String) extends Exception
  case class DeserialisationException(message: String, json: String) extends Exception

  implicit lazy val formats = org.json4s.DefaultFormats
  implicit lazy val serialization = native.Serialization

  def parse(s: String): Validated[Json.ParseException, JValue] =
    catchNonFatal[JValue](JsonParser.parse(s)).leftMap(e => Json.ParseException(e.getMessage))

  def print(json: JValue) = Printer.pretty(JsonMethods.render(json))

  def deserialise[A](
    json: JValue)(implicit f: Formats, m: scala.reflect.Manifest[A]
  ): Validated[DeserialisationException, A] =
    catchNonFatal[A](json.extract[A])
      .leftMap(e => DeserialisationException(e.getMessage, print(json)))

  def extract[A](
    s: String)(implicit f: Formats, m: scala.reflect.Manifest[A]
  ): Validated[Json.Exception, A] =
    parse(s).toEither.flatMap(deserialise(_).toEither).toValidated

  def serialise[A <: AnyRef](a: A)(implicit f: Formats, s: Serialization): String = s.write(a)

  def decompose(a: Any)(implicit f: Formats): JValue = Extraction.decompose(a)

}
