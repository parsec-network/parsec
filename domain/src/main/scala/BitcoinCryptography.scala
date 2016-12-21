package org.parsec.protocol

import org.bitcoinj.core.ECKey

trait BitcoinCryptography {
  // TODO: implement me
  def bitcoinP2PKHAddressFromPublicKey(key: String): String = {
    "btc-public-key"
  }

  // TODO: implement me
  def bitcoinP2SHAddressFromPublicKey(key: String): String = {
    "btc-public-key"
  }

  def bitcoinAddressFromPublicKey(key: String): String = {
    val key = new ECKey(key)
    "btc-public-key"
  }
}

// TODO: DELETE ME Example implementation
//
//001|package com.waferthin.bitcoinj;
//002|
//003|import com.google.bitcoin.core.ECKey;
//004|import com.google.bitcoin.core.NetworkParameters;
//005|import com.google.bitcoin.core.Address;
//006|
//007|public class CreateAddress {
//  008|
//  009|    public static void main(String[] args) throws Exception {
//    010|
//      011|        // use test net by default
//      012|        String net = "test";
//    013|
//      014|        if (args.length >= 1 && (args[0].equals("test") || args[0].equals("prod"))) {
//      015|            net = args[0];
//      016|            System.out.println("Using " + net + " network.");
//      017|        }
//    018|
//      019|        // create a new EC Key ...
//      020|        ECKey key = new ECKey();
//    021|
//      022|        // ... and look at the key pair
//      023|        System.out.println("We created key:\n" + key);
//    024|
//      025|        // either test or production net are possible
//      026|        final NetworkParameters netParams;
//    027|
//      028|        if (net.equals("prod")) {
//      029|            netParams = NetworkParameters.prodNet();
//      030|        } else {
//      031|            netParams = NetworkParameters.testNet();
//      032|        }
//    033|
//      034|        // get valid Bitcoin address from public key
//      035|        Address addressFromKey = key.toAddress(netParams);
//    036|
//      037|        System.out.println("On the " + net + " network, we can use this address:\n" + addressFromKey);
//    038|    }
//  039|}
