package com.saalamsaifi.spring.playground.service;

import com.saalamsaifi.spring.playground.request.PostExtractRequest;

import java.io.IOException;
import java.util.Map;

public interface IRealtimeOCRService {
  Map<String, String> extract(PostExtractRequest request) throws IOException;
}
