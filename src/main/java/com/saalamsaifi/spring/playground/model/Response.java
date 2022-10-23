package com.saalamsaifi.spring.playground.model;

import java.util.Map;
import lombok.Data;

@Data
public class Response {

  private Map<String, String> headers;
  private int status;
  private String body;
}
