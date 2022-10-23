package com.saalamsaifi.spring.playground.repository;

import java.util.List;

public interface ListRepository<T> {

  void addAll(List<T> collection);

  void add(T item);

  List<T> getAll();

  T find(String query);
}
