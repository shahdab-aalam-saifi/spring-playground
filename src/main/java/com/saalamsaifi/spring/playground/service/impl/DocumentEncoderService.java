package com.saalamsaifi.spring.playground.service.impl;

import com.saalamsaifi.spring.playground.service.IEncoderService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class DocumentEncoderService implements IEncoderService {
  @Override
  public File encode(String fileName, String fileType, String fileBytes) {
    var file = new File(fileName + "." + fileType);
    var decoder = Base64.getDecoder();
    var documentBytes = decoder.decode(fileBytes.getBytes(StandardCharsets.UTF_8));

    try (var fis = new FileOutputStream(file)) {
      fis.write(documentBytes);

      return file;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }
}
