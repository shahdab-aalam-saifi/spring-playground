package com.saalamsaifi.spring.playground.common.utils;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class EncoderUtils {
  private EncoderUtils() {}

  public static List<String> getImageTypes() {
    return List.of("png", "jpeg", "jpg", "tiff");
  }

  public static List<String> getDocumentTypes() {
    return List.of("pdf");
  }
}
