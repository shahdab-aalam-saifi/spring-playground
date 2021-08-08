package com.saalamsaifi.spring.playground.service.impl;

import com.saalamsaifi.spring.playground.common.utils.EncoderUtils;
import com.saalamsaifi.spring.playground.service.IEncoderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class EncoderServiceImpl implements IEncoderService {
  @Autowired private ImageEncoderService imageService;
  @Autowired private DocumentEncoderService documentService;

  @Override
  public File encode(String fileName, String fileType, String fileBytes) {
    if (EncoderUtils.getImageTypes().contains(fileType.toLowerCase())) {
      return imageService.encode(fileName, fileType, fileBytes);
    }

    if (EncoderUtils.getDocumentTypes().contains(fileType.toLowerCase())) {
      return imageService.encode(fileName, fileType, fileBytes);
    }

    throw new IllegalArgumentException("fileType is not supported!");
  }
}
