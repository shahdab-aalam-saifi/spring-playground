package com.saalamsaifi.spring.playground.service.impl;

import com.jayway.jsonpath.JsonPath;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import net.minidev.json.JSONArray;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientService {

  private final WebClient rWebClient;

  public WebClientService(WebClient.Builder builder
  ) {
    this.rWebClient = builder.baseUrl("https://reqres.in/api").build();
  }


  public List<Object> getUsersApi() {
    final ResponseEntity<String> response = rWebClient.get()
      .uri("/users?page=2&delay=2")
      .retrieve()
      .toEntity(String.class)
      .block();

    if (Objects.isNull(response) || !HttpStatus.OK.equals(response.getStatusCode())) {
      return Collections.emptyList();
    }

    JSONArray users = JsonPath.read(response.getBody(), "$..data[*]");

    return new ArrayList<>(users);
  }

  public String postRegisterApi() {
    final HashMap<String, String> bodyMap = new HashMap<>();

    bodyMap.put("email", "eve.holt@reqres.in");
    bodyMap.put("password", "pistol");

    final ResponseEntity<String> response = rWebClient.post()
      .uri("/register")
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(BodyInserters.fromValue(bodyMap))
      .retrieve().toEntity(String.class)
      .onErrorComplete()
      .block();

    if (Objects.isNull(response) || !HttpStatus.OK.equals(response.getStatusCode())) {
      return Strings.EMPTY;
    }

    return JsonPath.read(response.getBody(), "token");
  }
}
