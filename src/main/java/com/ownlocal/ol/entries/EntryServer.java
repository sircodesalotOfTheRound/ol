package com.ownlocal.ol.entries;

import com.ownlocal.ol.datastructures.RangeTree;
import com.ownlocal.ol.ingest.EntryLoader;
import com.ownlocal.ol.run.endpoints.BusinessesResponse;

import java.util.List;
import java.util.Optional;

public class EntryServer {
  public static final String ENTRY_SERVER_KEY = "business-server";

  private RangeTree rangeTree;
  private EntryServer(String path) {
    this.rangeTree = new RangeTree(loadEntries(path));
  }

  private Business[] loadEntries(String path) {
    try {
      return new EntryLoader(path).load();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public static EntryServer loadFromFile(String path) {
    return new EntryServer(path);
  }

  public BusinessesResponse getPage(int page, int pageSize) {
    List<Business> results = rangeTree.query(page * pageSize, (page + 1) * pageSize - 1);
    return new BusinessesResponse(page, pageSize, rangeTree.size(), results);
  }

  public Optional<Business> getBusinessAtIndex(int index) {
    if (rangeTree.contains(index)) {
      return Optional.of(rangeTree.getAtIndex(index));
    } else {
      return Optional.empty();
    }
  }

  public int totalItems() {
    return rangeTree.size();
  }
}
