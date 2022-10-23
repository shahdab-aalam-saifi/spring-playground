package com.saalamsaifi.spring.playground.controller;

import com.saalamsaifi.spring.playground.cache.GuavaCache;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cache")
public class CacheController {

  private final GuavaCache<String> uuidCache;

  public CacheController(GuavaCache<String> uuidCache) {
    this.uuidCache = uuidCache;
  }

  @GetMapping("/resource")
  public Map<String, String> getResource() {
    var map = new HashMap<String, String>();

    var uuid = uuidCache.get("uuid");

    if (Objects.isNull(uuid)) {
      uuid = getUuid();
      uuidCache.add("uuid", uuid);
    }

    map.put("uuid", uuid);
    map.put("timestamp", String.valueOf(System.currentTimeMillis()));

    return map;
  }

  public String getUuid() {
    return UUID.randomUUID().toString();
  }
}
