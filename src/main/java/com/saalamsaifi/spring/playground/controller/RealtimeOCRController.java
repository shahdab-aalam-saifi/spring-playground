package com.saalamsaifi.spring.playground.controller;

import com.saalamsaifi.spring.playground.request.PostExtractRequest;
import com.saalamsaifi.spring.playground.service.IRealtimeOCRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/realtime")
public class RealtimeOCRController {
  @Autowired private IRealtimeOCRService service;

  @PostMapping("/extract")
  public String postExtract(@RequestBody PostExtractRequest request) {
    return service.extract(request);
  }
}
