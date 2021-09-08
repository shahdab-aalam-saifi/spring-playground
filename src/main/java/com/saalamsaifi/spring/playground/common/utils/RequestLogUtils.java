package com.saalamsaifi.spring.playground.common.utils;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public final class RequestLogUtils {
  private RequestLogUtils() {}

  //// Request
  public static List<String> getRequestHeaders() {
    return List.of("ClientId", "ConsumerId", "SystemEntCd");
  }

  public static List<String> getRequestParams() {
    return Collections.emptyList();
  }

  //// Response
  public static List<String> getResponseHeaders() {
    return Collections.emptyList();
  }
}
