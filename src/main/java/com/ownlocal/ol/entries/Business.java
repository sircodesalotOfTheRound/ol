package com.ownlocal.ol.entries;

import com.ownlocal.ol.Lexer;

import java.util.UUID;

public class Business {
  private final int id;
  private final UUID uuid;
  private final String name;
  private final String address;
  private final String address2;
  private final String city;
  private final String state;
  private final String country;
  private final String phone;
  private final String website;
  private final String createdAt;

  public Business(String line) {
    Lexer lexer = new Lexer(line);

    this.id = lexNumber(lexer);
    this.uuid = lexUUID(lexer);
    this.name = lexName(lexer);
    this.address = lexEntry(lexer);
    this.address2 = lexEntry(lexer);
    this.city = lexEntry(lexer);
    this.state = lexEntry(lexer);
    this.country = lexEntry(lexer);
    this.phone = lexEntry(lexer);
    this.website = lexEntry(lexer);
    this.createdAt = lexEntry(lexer);
  }

  private int lexNumber(Lexer lexer) {
    int number = 0;
    while (lexer.currentIs(Character::isDigit)) {
      number = number * 10 + Character.getNumericValue(lexer.readCurrentAndAdvance());
    }

    lexer.readCurrentAndAdvance(',');
    return number;
  }

  private UUID lexUUID(Lexer lexer) {
    StringBuilder builder = new StringBuilder();
    while (!lexer.currentIs(',')) {
      builder.append(lexer.readCurrentAndAdvance());
    }

    lexer.readCurrentAndAdvance(',');
    return UUID.fromString(builder.toString());
  }

  private String lexName(Lexer lexer) {
    StringBuilder builder = new StringBuilder();
    if (lexer.currentIs('"')) {
      lexer.readCurrentAndAdvance('"');
      while (!lexer.currentIs('"')) {
        builder.append(lexer.readCurrentAndAdvance());
      }
      lexer.readCurrentAndAdvance('"');
    } else {
      while (!lexer.currentIs(',')) {
        builder.append(lexer.readCurrentAndAdvance());
      }
    }

    lexer.readCurrentAndAdvance(',');
    return builder.toString();
  }

  private String lexEntry(Lexer lexer) {
    StringBuilder builder = new StringBuilder();
    while (!lexer.currentIs(',')) {
      builder.append(lexer.readCurrentAndAdvance());
    }

    lexer.readCurrentAndAdvance(',');
    return builder.toString();
  }

  public int getId() { return this.id; }
  public UUID getUuid() { return this.uuid; }
  public String getName() { return this.name; }
  public String getAddress() { return this.address; }
  public String getAddress2() { return this.address2; }
  public String getCity() { return this.city; }
  public String getState() { return this.state; }
  public String getCountry() { return this.country; }
  public String getPhone() { return this.phone; }
  public String getWebsite() { return this.website; }
  public String getCreatedAt() { return this.createdAt; }

  @Override
  public String toString() {
    return String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
      this.id, this.uuid, this.name, this.address, this.address2,
      this.city, this.state, this.country, this.phone, this.website,
      this.createdAt);
  }
}
