package com.ownlocal.ol.datastructures;

import com.ownlocal.ol.entries.Business;
import com.ownlocal.ol.validation.Validation;

import java.util.ArrayList;
import java.util.List;

public class RangeTree {
  public class Node {
    public Business business;
    Node lhs;
    Node rhs;
    public Node(Business business, Node lhs, Node rhs) {
      this.business = business;
      this.lhs = lhs;
      this.rhs = rhs;
    }

    public int index() { return business.getId(); }
    public Business business() { return business; }
  }

  private final Node root;
  private final int size;
  public RangeTree(Business[] entries) {
    this.root = balancedInsert(entries, 0, entries.length - 1);
    this.size = entries.length;
  }

  private Node balancedInsert(Business[] entries, int lhs, int rhs) {
    if (lhs > rhs) return null;

    int mid = lhs + (rhs - lhs) / 2;
    return new Node(entries[mid],
      balancedInsert(entries, lhs, mid - 1),
      balancedInsert(entries, mid + 1, rhs));
  }

  public int size() { return this.size; }

  public Business getAtIndex(int index) {
    Validation.check(contains(index), "No such node found");
    return find(root, index).business();
  }

  public boolean contains(int index) {
    return find(root, index) != null;
  }

  private Node find(Node node, int index) {
    if (node == null) return null;
    if (index == node.index()) return node;

    if (index < node.index()) {
      return find(node.lhs, index);
    } else {
      return find(node.rhs, index);
    }
  }

  public List<Business> query(int start, int end) {
    return query(root, start, end, new ArrayList<>());
  }

  private List<Business> query(Node node, int start, int end, List<Business> result) {
    if (node == null) return result;

    if (start <= node.index()) query(node.lhs, start, end, result);
    if (start <= node.index() && end >= node.index()) result.add(node.business());
    if (end >= node.index()) query(node.rhs, start, end, result);

    return result;
  }
}
