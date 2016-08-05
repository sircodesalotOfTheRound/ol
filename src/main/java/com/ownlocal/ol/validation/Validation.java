package com.ownlocal.ol.validation;

import com.ownlocal.ol.exception.ValidationException;

public class Validation {
  public static void check(boolean test, String format, Object... args) {
    if (!test) throw new ValidationException(format, args);
  }
}
