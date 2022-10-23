package com.saalamsaifi.spring.playground.common.utils;

import static com.saalamsaifi.spring.playground.common.enums.FileType.JPEG;
import static com.saalamsaifi.spring.playground.common.enums.FileType.JPG;
import static com.saalamsaifi.spring.playground.common.enums.FileType.PDF;
import static com.saalamsaifi.spring.playground.common.enums.FileType.PNG;
import static com.saalamsaifi.spring.playground.common.enums.FileType.TIFF;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public final class EncoderUtils {

  private EncoderUtils() {
  }

  public static List<String> getImageTypes() {
    return List.of(
      PNG.getExtension(), JPG.getExtension(), JPEG.getExtension(), TIFF.getExtension());
  }

  public static List<String> getDocumentTypes() {
    return List.of(PDF.getExtension());
  }
}
