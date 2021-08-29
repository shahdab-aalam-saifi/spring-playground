package com.saalamsaifi.spring.playground;

import com.saalamsaifi.spring.playground.model.Person;
import com.saalamsaifi.spring.playground.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class SpringPlaygroundApplication implements ApplicationRunner {
  @Autowired
  private ListRepository<Person> repository;

  public static void main(String[] args) {
    SpringApplication.run(SpringPlaygroundApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    var personList = new ArrayList<Person>();

    personList.add(
      Person.builder()
        .firstName("Erminie")
        .lastName("Zaniolini")
        .email("ezaniolini0@google.co.uk")
        .build());

    personList.add(
      Person.builder().firstName("Errol").lastName("Cord").email("ecord1@xing.com").build());

    personList.add(
      Person.builder()
        .firstName("Jasmine")
        .lastName("Bartelli")
        .email("jbartelli2@purevolume.com")
        .build());

    personList.add(
      Person.builder()
        .firstName("Hirsch")
        .lastName("Dearnly")
        .email("hdearnly3@aboutads.info")
        .build());

    repository.addAll(personList);
  }
}
