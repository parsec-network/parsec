package org.parsec

trait ParsecException extends Exception {
  def message: String
}
