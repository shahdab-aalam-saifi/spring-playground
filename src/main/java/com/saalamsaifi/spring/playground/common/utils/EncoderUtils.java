package com.saalamsaifi.spring.playground.common.utils;

import org.springframework.stereotype.Component;

import java.util.List;

import static com.saalamsaifi.spring.playground.common.enums.FileType.*;

@Component
public final class EncoderUtils {
  private EncoderUtils() {}

  public static List<String> getImageTypes() {
    return List.of(
        PNG.getExtension(), JPG.getExtension(), JPEG.getExtension(), TIFF.getExtension());
  }

  public static List<String> getDocumentTypes() {
    return List.of(PDF.getExtension());
  }
}
