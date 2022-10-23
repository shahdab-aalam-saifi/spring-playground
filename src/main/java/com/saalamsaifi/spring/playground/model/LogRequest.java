package com.saalamsaifi.spring.playground.model;

import lombok.Data;

@Data
public class LogRequest {

  private String url;
  private String clientId;
  private Request request;
  private Response response;
}
