package com.saalamsaifi.spring.playground.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saalamsaifi.spring.playground.service.IClientInfoExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

@Service
public class ClientInfoExtractServiceImpl implements IClientInfoExtractService {
  public static final String NOT_AVAILABLE = "Not Available";

  @Autowired private ObjectMapper mapper;

  @Override
  public String getClientId(String authorization) {
    if (Objects.isNull(authorization) || !authorization.startsWith("Bearer ")) {
      return NOT_AVAILABLE;
    }

    var payload = authorization.substring(7).split("\\.");

    if (payload.length < 3) {
      return NOT_AVAILABLE;
    }

    var decodePayload = decodeBase64Payload(payload[1]);

    return getValue(decodePayload, "ClientId");
  }

  private String getValue(String src, String field) {
    JsonNode node;
    try {
      node = mapper.readTree(src);
    } catch (JsonProcessingException e) {
      return NOT_AVAILABLE;
    }
    return Objects.nonNull(node.get(field)) ? node.get(field).asText(): NOT_AVAILABLE;
  }

  private String decodeBase64Payload(String src) {
    return new String(Base64.getDecoder().decode(src), StandardCharsets.UTF_8);
  }
}
