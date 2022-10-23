package com.saalamsaifi.spring.playground.controller;

import com.saalamsaifi.spring.playground.service.impl.WebClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/web")
public class WebClientController {

  @Autowired
  private WebClientService webClientService;

  @GetMapping(value = "/getUsersApi")
  public ResponseEntity<List<?>> foo() {
    List<?> list = webClientService.getUsersApi();

    return list.isEmpty() ? ResponseEntity.notFound().build()
      : ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(list);
  }

  @GetMapping(value = "/postRegisterApi")
  public ResponseEntity<String> postRegisterApi() {
    String token = webClientService.postRegisterApi();

    return token.isEmpty() ? ResponseEntity.notFound().build()
      : ResponseEntity.ok().body(token);
  }
}
