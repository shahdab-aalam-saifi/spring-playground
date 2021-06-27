package com.saalamsaifi.spring.playground.repository;

import com.saalamsaifi.spring.playground.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository implements ListRepository<Person> {
  private final List<Person> list;

  @Autowired
  public PersonRepository() {
    this.list = new ArrayList<>();
  }

  @Override
  public void addAll(List<Person> collection) {
    if (collection == null || collection.isEmpty()) {
      throw new IllegalArgumentException("Collection is empty!");
    }

    list.addAll(new ArrayList<>(collection));
  }

  @Override
  public void add(Person item) {
    if (item == null) {
      throw new IllegalArgumentException("Person is null!");
    }

    list.add(item);
  }

  @Override
  public List<Person> getAll() {
    return new ArrayList<>(list);
  }

  @Override
  public Person find(String firstName) {

    for (Person person : list) {
      if (person.getFirstName() != null && person.getFirstName().equalsIgnoreCase(firstName)) {
        return person;
      }
    }

    return null;
  }
}
