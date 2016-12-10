# Parsec

Contains the Parsec specifications, and an implementation of the protocol using Kafka

## Run

To run the ParsecSimulator that will generate Parsec Protocol messages on Kafka topics:
Just edit the KProducer.scala run:

    sbt run

## Kafka cluster

You can see the **schemas** as they are registered in the
[schema-registry-ui](http://schema-registry-ui.landoop.com/#/schema/PARSEC-PAYMENTS-value/version/latest)

You can view the **messages** as they are shown in the
[kafka-topics-ui](http://kafka-topics-ui.landoop.com/#/topic/n/PARSEC-PAYMENTS/rawdata)

## A bit of theory

 https://github.com/parsec-network/parsec/blob/specs/docs/specs/parsec_pay_setup.png
 
 https://github.com/parsec-network/parsec/blob/specs/docs/specs/customer_journey_parsec_payment.png

* Study Hashed Time Lock -> https://rusty.ozlabs.org/?p=462 to understand how the smart contract happens
* On HTLs -> https://rusty.ozlabs.org/?p=462
* Lightning network ->  https://lightning.network/lightning-network-paper.pdf

## TODO

Blog /blog/2016/12/parsec-scaling-cryptocurrency-with-kafka/
