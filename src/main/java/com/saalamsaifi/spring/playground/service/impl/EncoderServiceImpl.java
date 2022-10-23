package com.saalamsaifi.spring.playground.service.impl;

import com.saalamsaifi.spring.playground.common.utils.EncoderUtils;
import com.saalamsaifi.spring.playground.service.IEncoderService;
import java.io.File;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("encode")
public class EncoderServiceImpl implements IEncoderService {

  private final ImageEncoderService imageService;
  private final DocumentEncoderService documentService;

  public EncoderServiceImpl(
    @Qualifier("image") ImageEncoderService imageService,
    @Qualifier("document") DocumentEncoderService documentService) {
    this.imageService = imageService;
    this.documentService = documentService;
  }

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
