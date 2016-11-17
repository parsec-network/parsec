# Parsec Network High Level Technical Specification

## Introduction

Parsec is a web-scale state channel for the Internet of Value

State channels can be used to solve speed, scalability and confidentiality problems of public distributed ledgers. A web-scale solution for State Channels is needed to enable a layer of value transfer to the internet.

Other proposals for State Channels include Raiden for Ethereum and Lightning Network for Bitcoin. However, we intend to leverage existing web-scale technologies used by large Internet companies like Uber, LinkedIn or Netflix. We'll use Apache Kafka that has been proven to scale to trillions of operations per day.

## Parsec Nodes

Parsec nodes will be job instances of the Parsec Library implementing the Parsec SDK. The Parsec Library will be built on top of the Kafka Streams library with the following added functionality:

- Support for a catalogue of different kinds of transactions
- Keeping an up to date state for all unique Ids of the transactions topic
- Interacting with Ethereum Smart contracts (e.g. Hashed Time Locks) for the topic partitions under the node's supervision for scheduled settlements or under the request of the control topic.

All functionality for each Parsec job will relate only to the partitions under it's control.

## Parsec Transactions

A basic catalogue of predefined transactions will be implemented. It should be simple to add additional custom transactions.

### Micro payment
This transaction will perform the following actions:

- Keep a sliding array of the latest `n` transactions in order to reconstruct the exact order of the transactions from the transaction hash pointers. This is needed in order to cope with our of order messages or repeated messages.

- Calculate a balance after each transaction.

- Commit the balance to the Smart Contract in the Ethereum network at predefined intervals. Intervals will be defined by the modulo `m` of the incremental transaction sequence number.

- Commit the balance to the Smart Contract in the Ethereum network at predefined timeouts. The timeout duration will be specified by the `checkpoint_timeout` parameter.

- Under the request of the control topic, perform an unscheduled settlement request to the Smart Contract.

- Under the request of the control topic, perform a disputed settlement request to the Smart Contract by means of transferring the signed transactions since the last settlement.

## Initialisation and Control


. to build an up to date

 PoC will include:

Container based Parsec node allowing blockchain-like Kafka topics
Leveraging Kafka Connect for ledger replication between nodes
Development of a Hashed Timelock Contract on Ethereum
Leveraging Kafka Streams for State keeping and Ethereum Smart Contract interaction

It is intended to show a PoC transferring 100k micro-payments per second. Apache Kafka, Scala or Solidity devs welcome. The project code will be open sourced. There's an architectural white paper, but there'll be a chance to influence architectural decisions.
