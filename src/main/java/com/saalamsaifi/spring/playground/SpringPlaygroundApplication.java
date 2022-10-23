package com.saalamsaifi.spring.playground;

import com.saalamsaifi.spring.playground.controller.JwtController;
import com.saalamsaifi.spring.playground.interceptor.JwtRequestFilter;
import com.saalamsaifi.spring.playground.model.Person;
import com.saalamsaifi.spring.playground.repository.ListRepository;
import com.saalamsaifi.spring.playground.security.JwtAuthenticationEntryPoint;
import com.saalamsaifi.spring.playground.security.WebSecurityConfig;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(excludeFilters = {
  @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {JwtRequestFilter.class,
    JwtAuthenticationEntryPoint.class, WebSecurityConfig.class, JwtController.class})})
@EnableCaching
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
