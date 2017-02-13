package org.parsec
package restapi
package marshalling

import akka.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import akka.http.scaladsl.model.{ContentType, HttpCharsets, MediaTypes}
import akka.http.scaladsl.unmarshalling.{FromEntityUnmarshaller, Unmarshaller}
import org.json4s.{Formats, Serialization, native}

import json.Json
import JsonMarshalling._

trait JsonMarshalling {

  implicit lazy val formats = Json.formats
  implicit lazy val serialization = native.Serialization

  implicit def unmarshaller[A : RestApiMarshallable : Manifest](implicit formats: Formats): FromEntityUnmarshaller[A] =
    Unmarshaller
      .byteStringUnmarshaller
      .forContentTypes(ContentType(MediaTypes.`application/json`))
      .mapWithCharset { (bytes, charset) =>
        val receivedData: String =
          if (charset == HttpCharsets.`UTF-8`) bytes.utf8String else bytes.decodeString(charset.nioCharset.name)
        Json.extract(receivedData).fold(
          exception => throw new UnmarshallingException(exception.message),
          obj => obj
        )
      }

  implicit def marshaller[A : RestApiMarshallable : Manifest](
    implicit serialization: Serialization, formats: Formats
  ): ToEntityMarshaller[A] =
    Marshaller.StringMarshaller.wrap(MediaTypes.`application/json`)(obj => Json.serialise(Json.decompose(obj)))

}

object JsonMarshalling {
  sealed trait Exception extends ParsecException
  case class UnmarshallingException(message: String) extends Exception
}
