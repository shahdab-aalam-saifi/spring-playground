package com.saalamsaifi.spring.playground.cache;

import org.apache.commons.collections4.map.LRUMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryCache<K, V> {
  private final long ttl;
  private final LRUMap map;

  protected class CacheObject {
    public long lastAccessedAt = System.currentTimeMillis();
    public V value;

    protected CacheObject(V value) {
      this.value = value;
    }
  }

  public InMemoryCache(long ttl, final long interval, int maxItems) {
    this.ttl = ttl * 1000;
    this.map = new LRUMap<>(maxItems);

    if (ttl > 0 && interval > 0) {
      Runnable task =
          () -> {
            while (true) {
              try {
                Thread.sleep(interval * 1000);
              } catch (InterruptedException e) {
                // ignore
              }
              cleanUp();
            }
          };

      var t = new Thread(task);
      t.setDaemon(true);
      t.start();
    }
  }

  public void put(K key, V value) {
    synchronized (map) {
      map.put(key, new CacheObject(value));
    }
  }

  @SuppressWarnings("unchecked")
  public V get(K key) {
    synchronized (map) {
      var cacheObject = (CacheObject) map.get(key);

      if (Objects.isNull(cacheObject)) {
        return null;
      } else {
        cacheObject.lastAccessedAt = System.currentTimeMillis();
        return cacheObject.value;
      }
    }
  }

  public void remove(K key) {
    synchronized (map) {
      map.remove(key);
    }
  }

  public int size() {
    synchronized (map) {
      return map.size();
    }
  }

  @SuppressWarnings("unchecked")
  public void cleanUp() {
    var now = System.currentTimeMillis();

    List<K> deleteKey = null;

    synchronized (map) {
      var itr = map.mapIterator();

      deleteKey = new ArrayList<>((map.size() / 2) + 1);

      K key = null;
      CacheObject cacheObject = null;

      while (itr.hasNext()) {
        key = (K) itr.next();
        cacheObject = (CacheObject) itr.getValue();

        if (Objects.nonNull(cacheObject) && (now > ttl + (cacheObject.lastAccessedAt))) {
          deleteKey.add(key);
        }
      }
    }

    for (K key : deleteKey) {
      synchronized (map) {
        map.remove(key);
      }

      Thread.yield();
    }
  }
}
