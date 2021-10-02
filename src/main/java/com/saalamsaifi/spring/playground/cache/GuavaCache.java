package com.saalamsaifi.spring.playground.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class GuavaCache<T> {
  private final Cache<String, T> cache;

  public GuavaCache(int expiryDuration, TimeUnit timeUnit) {
    this.cache =
        CacheBuilder.newBuilder()
            .expireAfterWrite(expiryDuration, timeUnit)
            .concurrencyLevel(Runtime.getRuntime().availableProcessors())
            .build();
  }

  public T get(String key) {
    return cache.getIfPresent(key);
  }

  public void add(String key, T value) {
    if (Objects.nonNull(key) && Objects.nonNull(value)) {
      cache.put(key, value);
    }
  }
}
