package com.ownlocal.ol.run.endpoints;

import com.ownlocal.ol.entries.Business;

import java.util.List;

public class BusinessesResponse {
  private final int pageSize;
  private int pageNumber;
  private int totalEntries;
  private int itemsOnPage;
  private Iterable<Business> businesses;

  public BusinessesResponse(int pageNumber, int pageSize,
                            int totalEntries, List<Business> businesses) {
    this.pageNumber = pageNumber;
    this.itemsOnPage = businesses.size();
    this.pageSize = pageSize;
    this.totalEntries = totalEntries;
    this.businesses = businesses;
  }

  public int getPageNumber() {
    return this.pageNumber;
  }

  public int getItemsOnPage() {
    return this.itemsOnPage;
  }

  public int getTotalEntries() {
    return this.totalEntries;
  }

  public int getOfPages() {
    if (pageSize == 0) return 0;
    return totalEntries / pageSize;
  }

  public int getPagesRemaining() {
    return (getOfPages() - pageNumber);
  }

  public Iterable<Business> getBusinesses() {
    return this.businesses;
  }
}
