package com.saalamsaifi.spring.playground.model;

import java.util.Map;
import lombok.Data;

@Data
public class Request {

  private Map<String, String> parameters;
  private Map<String, String> headers;
  private String body;
}
