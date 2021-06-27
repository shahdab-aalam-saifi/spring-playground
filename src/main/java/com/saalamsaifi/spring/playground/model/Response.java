package com.saalamsaifi.spring.playground.model;

import lombok.Data;

import java.util.Map;

@Data
public class Response {
  private Map<String, String> headers;
  private int status;
  private String body;
}
