package com.saalamsaifi.spring.playground.service;

import com.saalamsaifi.spring.playground.model.Person;

public interface IInterceptorService {
    Person getPersonByFirstName(String firstName);
}
