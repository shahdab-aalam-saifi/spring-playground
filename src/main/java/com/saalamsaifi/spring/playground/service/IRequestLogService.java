package com.saalamsaifi.spring.playground.service;

import com.saalamsaifi.spring.playground.model.Request;
import com.saalamsaifi.spring.playground.model.Response;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.UnsupportedEncodingException;

public interface IRequestLogService {
  Request logRequestInfo(ContentCachingRequestWrapper requestWrapper) throws UnsupportedEncodingException;

  Response logResponseInfo(ContentCachingResponseWrapper responseWrapper) throws UnsupportedEncodingException;
}
