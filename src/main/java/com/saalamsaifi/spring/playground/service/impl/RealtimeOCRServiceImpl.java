package com.saalamsaifi.spring.playground.service.impl;

import com.saalamsaifi.spring.playground.request.PostExtractRequest;
import com.saalamsaifi.spring.playground.service.IEncoderService;
import com.saalamsaifi.spring.playground.service.IRealtimeOCRService;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RealtimeOCRServiceImpl implements IRealtimeOCRService {

  private final IEncoderService encoderService;

  public RealtimeOCRServiceImpl(@Qualifier("encode") IEncoderService encoderService) {
    this.encoderService = encoderService;
  }

  @Override
  public Map<String, String> extract(PostExtractRequest request) throws IOException {
    var file =
      encoderService.encode(request.getFileName(), request.getFileType(), request.getFileBytes());

    var map = new HashMap<String, String>();

    if (Objects.isNull(file)) {
      map.put("fileDelete", String.valueOf(false));
      map.put("error", "file = null");

      return map;
    }

    map.put("fileName", file.getName());
    map.put("filePath", file.getAbsolutePath());
    map.put("fileSize", String.valueOf(Files.size(file.toPath())));

    try {
      Files.delete(file.toPath());
    } catch (IOException e) {
      map.put("fileDelete", String.valueOf(false));
      map.put("error", e.getMessage());

      return map;
    }

    map.put("fileDelete", String.valueOf(true));
    return map;
  }
}
