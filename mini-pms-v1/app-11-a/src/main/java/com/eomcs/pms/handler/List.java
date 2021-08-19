package com.eomcs.pms.handler;

public interface List {
  void add(Object item);
  Object[] toArray();
  boolean remove(Object obj);
  int size();
  Object get(int index);
  Object remove(int index);
}









