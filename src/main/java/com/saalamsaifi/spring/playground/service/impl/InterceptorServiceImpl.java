package com.saalamsaifi.spring.playground.service.impl;

import com.saalamsaifi.spring.playground.model.Person;
import com.saalamsaifi.spring.playground.repository.ListRepository;
import com.saalamsaifi.spring.playground.service.IInterceptorService;
import org.springframework.stereotype.Service;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
public class InterceptorServiceImpl implements IInterceptorService {
  private final ListRepository<Person> repository;

  public InterceptorServiceImpl(ListRepository<Person> repository) {
    this.repository = repository;
  }

  @Override
  public Person getPersonByFirstName(String firstName) {
    if (!isBlank(firstName)) {
      return repository.find(firstName);
    }

    return null;
  }
}
