package com.ownlocal.ol;

import com.ownlocal.ol.validation.Validation;

import java.util.function.Predicate;

public class Lexer {
  private final String line;
  private int index;

  public Lexer(String line) {
    this.line = line;
    this.index = 0;
  }

  public boolean isEof() {
    return index == line.length();
  }

  public char current() {
    return line.charAt(index);
  }

  public void advance() {
    this.index++;
  }

  public char readCurrentAndAdvance() {
    char letter = current();
    this.advance();
    return letter;
  }

  public char readCurrentAndAdvance(char letter) {
    Validation.check(currentIs(letter), "Expected %s", letter);
    return readCurrentAndAdvance();
  }

  public boolean currentIs(Predicate<Character> predicate) {
    return !isEof() && predicate.test(current());
  }

  public boolean currentIs(char letter) {
    return !isEof() && (current() == letter);
  }
}
