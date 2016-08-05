package com.ownlocal.ol.ingest;

import com.ownlocal.ol.entries.Business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by reubenkuhnert on 8/4/16.
 */
public class EntryLoader {
  private final String path;
  public EntryLoader(String path) {
    this.path = path;
  }

  public Business[] load() throws IOException {
    List<Business> entries = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
      String value = reader.readLine(); // Read the header
      while ((value = reader.readLine()) != null) {
        entries.add(new Business(value));
      }
    }

    return entries.toArray(new Business[entries.size()]);
  }
}
