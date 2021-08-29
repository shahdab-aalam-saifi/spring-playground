package com.saalamsaifi.spring.playground.service.impl;

import com.saalamsaifi.spring.playground.request.PostExtractRequest;
import com.saalamsaifi.spring.playground.service.IEncoderService;
import com.saalamsaifi.spring.playground.service.IRealtimeOCRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RealtimeOCRServiceImpl implements IRealtimeOCRService {
  @Qualifier("encode")
  @Autowired
  private IEncoderService encoderService;

  @Override
  public String extract(PostExtractRequest request) {
    encoderService.encode(request.getFileName(), request.getFileType(), request.getFileBytes());
    return "success";
  }
}
