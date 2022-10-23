package com.saalamsaifi.spring.playground.cache;

import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheStore {

  @Bean
  public GuavaCache<String> uuidCache() {
    return new GuavaCache<>(15, TimeUnit.SECONDS);
  }
}
