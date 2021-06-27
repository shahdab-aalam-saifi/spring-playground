package com.saalamsaifi.spring.playground.model;

import lombok.Data;

@Data
public class LogRequest {
  private Request request;
  private Response response;
}
