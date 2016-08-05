package com.ownlocal.ol.tools;

/**
 * Created by reubenkuhnert on 8/4/16.
 */
public class Safe {
  public static int integer(Integer value) {
    if (value == null) {
      return 0;
    } else {
      return value;
    }
  }

  public static int integer(Integer value, int defaultValue) {
    if (value == null) {
      return defaultValue;
    } else {
      return value;
    }
  }
}
