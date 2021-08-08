package com.saalamsaifi.spring.playground.controller;

import com.saalamsaifi.spring.playground.common.exception.NoPersonFoundException;
import com.saalamsaifi.spring.playground.model.Person;
import com.saalamsaifi.spring.playground.service.IInterceptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class InterceptorController {
  private final IInterceptorService service;

  @Autowired
  public InterceptorController(IInterceptorService service) {
    this.service = service;
  }

  @GetMapping("/getPerson")
  public ResponseEntity<Person> getPerson(@RequestParam(required = false) String firstName) {
    var person = service.getPersonByFirstName(firstName);

    if (person != null) {
      return ResponseEntity.ok(person);
    }

    throw new NoPersonFoundException(firstName);
  }
}
