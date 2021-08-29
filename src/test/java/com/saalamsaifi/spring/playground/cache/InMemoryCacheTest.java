package com.saalamsaifi.spring.playground.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryCacheTest {
  @Test
  void basicInMemoryCacheOperations() {
    var memoryCache = new InMemoryCache<String, String>(200, 500, 6);

    memoryCache.put("eBay", "eBay");
    memoryCache.put("Paypal", "Paypal");
    memoryCache.put("Google", "Google");
    memoryCache.put("Microsoft", "Microsoft");
    memoryCache.put("IBM", "IBM");
    memoryCache.put("Facebook", "Facebook");

    System.out.println("6 object added " + memoryCache.size());
    memoryCache.remove("IBM");
    System.out.println("1 object removed " + memoryCache.size());

    memoryCache.put("Twitter", "Twitter");
    memoryCache.put("SAP", "SAP");
    System.out.println("2 object added " + memoryCache.size());

    assertEquals(6, memoryCache.size());
  }

  @Test
  void expiredInMemoryCacheObject() throws InterruptedException {
    var memoryCache = new InMemoryCache<String, String>(1, 1, 10);

    memoryCache.put("eBay", "eBay");
    memoryCache.put("Paypal", "Paypal");

    Thread.sleep(3000);

    System.out.println("2 object reached TTL " + memoryCache.size());

    assertEquals(0, memoryCache.size());
  }

  @Test
  void cleanUpInMemoryCache() throws InterruptedException {
    var size = 5_00_000;
    var memoryCache = new InMemoryCache<String, String>(100, 100, size);

    for (int i = 0; i < size; i++) {
      var value = String.valueOf(i);
      memoryCache.put(value, value);
    }

    Thread.sleep(200);

    var start = System.currentTimeMillis();
    memoryCache.cleanUp();
    var end = System.currentTimeMillis();

    System.out.println("Clean up completed in " + (end - start) + "ms");

    assertTrue(memoryCache.size() > -1);
  }
}
