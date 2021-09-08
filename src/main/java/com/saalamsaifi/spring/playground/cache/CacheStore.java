package com.saalamsaifi.spring.playground.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheStore {
  @Bean
  public GuavaCache<String> uuidCache() {
    return new GuavaCache<>(15, TimeUnit.SECONDS);
  }
}
