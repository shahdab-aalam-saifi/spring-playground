package com.saalamsaifi.spring.playground.common.enums;

public enum FileType {
  PNG("png"),
  JPG("jpg"),
  PDF("pdf"),
  TIFF("tiff"),
  JPEG("jpeg");

  private final String extension;

  FileType(String extension) {
    this.extension = extension;
  }

  public String getExtension() {
    return extension;
  }
}
