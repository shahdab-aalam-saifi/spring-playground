package com.saalamsaifi.spring.playground.controller;

import com.saalamsaifi.spring.playground.request.PostExtractRequest;
import com.saalamsaifi.spring.playground.service.IRealtimeOCRService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/v1/realtime")
public class RealtimeOCRController {
  private final IRealtimeOCRService service;

  public RealtimeOCRController(IRealtimeOCRService service) {
    this.service = service;
  }

  @PostMapping("/extract")
  public Map<String, String> postExtract(@RequestBody PostExtractRequest request)
      throws IOException {
    return service.extract(request);
  }
}
