# Parsec

Contains the Parsec specifications, and an implementation of the protocol using Kafka

## Run

To run the ParsecSimulator that will generate Parsec Protocol messages on Kafka topics:
Just edit the KProducer.scala run:

    sbt simulator/run

## Kafka cluster

You can see the **schemas** as they are registered in the
[schema-registry-ui](http://parsec.playground.landoop.com:43030/schema-registry-ui/#/schema/PARSEC-PAYMENTS-value/version/latest)

You can view the **messages** as they are shown in the
[kafka-topics-ui](http://parsec.playground.landoop.com:43030/kafka-topics-ui/#/topic/n/PARSEC-PAYMENTS/rawdata)

## A bit of theory

 https://github.com/parsec-network/parsec/blob/specs/docs/specs/parsec_pay_setup.png
 
 https://github.com/parsec-network/parsec/blob/specs/docs/specs/customer_journey_parsec_payment.png

* Study Hashed Time Lock -> https://rusty.ozlabs.org/?p=462 to understand how the smart contract happens
* On HTLs -> https://rusty.ozlabs.org/?p=462
* Lightning network ->  https://lightning.network/lightning-network-paper.pdf

## TODO

Blog /blog/2016/12/parsec-scaling-cryptocurrency-with-kafka/

Decide on State management implementation. KStreams is a good choice:
- partition aware (it auto handles partitioning when scaling)
- state keeping is like samza but improved from functionality from Google
- persists on RocksDB and compacted topics
- it persists on RocksDB and compacted topics
- it resumes from where it left when it crashes
