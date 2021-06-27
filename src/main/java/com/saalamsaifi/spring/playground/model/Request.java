package com.saalamsaifi.spring.playground.model;

import lombok.Data;

import java.util.Map;

@Data
public class Request {
  private String url;
  private Map<String, String> parameters;
  private Map<String, String> headers;
  private String body;
}
