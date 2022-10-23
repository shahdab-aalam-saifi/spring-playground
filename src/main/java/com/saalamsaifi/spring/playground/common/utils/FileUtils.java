package com.saalamsaifi.spring.playground.common.utils;

import java.util.UUID;

public final class FileUtils {

  private FileUtils() {
  }

  public static String getUniqueFileName(String fileName, String fileType) {
    return String.format(
      "%s-%s-%s.%s",
      UUID.randomUUID(),
      fileName.replaceAll("[^a-zA-Z0-9]", ""),
      System.currentTimeMillis(),
      fileType);
  }
}
