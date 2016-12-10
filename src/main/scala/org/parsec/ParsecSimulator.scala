package org.parsec

import com.github.nscala_time.time.Imports._
import org.parsec.ParsecProtocol.{HashPointer, SignedInvoice}
import scala.util.{Failure, Success, Try}

/**
  * A synthetic Parsec data generator: Creates and posts messages to Kafka topics to test the capabilities
  * and for system & performance evaluation
  *
  * @note This is hooked against an existing & public cloud based Kafka cluster
  * @see <b>KProducer<b> to find connection details
  */
object ParsecSimulator extends App {

  // Contains [SignedInvoice] messages (partition for scalability)
  val PARSEC_PAYMENTS_TOPIC = "PARSEC-PAYMENTS"

  val producer = new KProducer[Key, SignedInvoice]()
  val timestamp = DateTime.now().getMillis
  val buyerAddress = "hash-of-public-key" // has to be the Hash

  Try(producer.produce(PARSEC_PAYMENTS_TOPIC, Key(buyerAddress), getSignedInvoice)) match {
    case Success(m) =>
      val metadata = m.get()
      println("Success writing to Kafka topic:" + metadata.topic(),
        metadata.offset(),
        metadata.partition(),
        new DateTime(metadata.timestamp())) // Timestamp the msg in the metadata
    case Failure(f) => println("Failed writing to Kafka", f)
  }

  // The Value of the messages on the PARSEC_PAYMENTS_TOPIC
  def getSignedInvoice: SignedInvoice =
    SignedInvoice(
      invoiceSignature = "mySIG",
      supplierAddress = "myAddress",
      buyerPublicKey = "myBuyerPK-XXXX-XXXX",
      currency = "ETH",
      channel = "#channel-1",
      price = 0.1,
      hashPointerToPrevious = HashPointer("transaction-ID", "trancaction-Hash"))

}

// The Key of the messages on the PARSEC_PAYMENTS_TOPIC
case class Key(buyerAddress: String)


