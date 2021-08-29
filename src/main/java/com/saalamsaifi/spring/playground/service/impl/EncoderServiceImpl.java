package com.saalamsaifi.spring.playground.service.impl;

import com.saalamsaifi.spring.playground.common.utils.EncoderUtils;
import com.saalamsaifi.spring.playground.service.IEncoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Qualifier("encode")
public class EncoderServiceImpl implements IEncoderService {
  @Qualifier("image")
  @Autowired
  private ImageEncoderService imageService;

  @Qualifier("document")
  @Autowired
  private DocumentEncoderService documentService;

  @Override
  public File encode(String fileName, String fileType, String fileBytes) {
    if (EncoderUtils.getImageTypes().contains(fileType.toLowerCase())) {
      return imageService.encode(fileName, fileType, fileBytes);
    }

    if (EncoderUtils.getDocumentTypes().contains(fileType.toLowerCase())) {
      return documentService.encode(fileName, fileType, fileBytes);
    }

    throw new IllegalArgumentException("fileType is not supported!");
  }
}
