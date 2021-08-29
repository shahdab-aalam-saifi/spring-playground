package com.saalamsaifi.spring.playground.service;

import com.saalamsaifi.spring.playground.request.PostExtractRequest;

public interface IRealtimeOCRService {
  String extract(PostExtractRequest request);
}
