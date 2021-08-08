package com.saalamsaifi.spring.playground.request;

import lombok.Data;

@Data
public class PostExtractRequest {
  private String fileClassificationType;
  private String fileName;
  private String fileType;
  private String fileBytes;
}
