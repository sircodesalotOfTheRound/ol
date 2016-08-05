package com.ownlocal.ol.exception;

/**
 * Created by reubenkuhnert on 8/4/16.
 */
public class ValidationException extends RuntimeException {
  public ValidationException(String format, Object... args) {
    super(String.format(format, args));
  }
}
