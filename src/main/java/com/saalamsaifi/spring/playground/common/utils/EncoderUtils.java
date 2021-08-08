package com.saalamsaifi.spring.playground.common.utils;

import java.util.List;

public final class EncoderUtils {
  private EncoderUtils() {}

  public static List<String> getImageTypes() {
    return List.of("png", "jpeg", "jpg", "tiff");
  }

  public static List<String> getDocumentTypes() {
    return List.of("pdf");
  }
}
