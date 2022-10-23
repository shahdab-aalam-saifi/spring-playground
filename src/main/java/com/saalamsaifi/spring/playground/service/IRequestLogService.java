package com.saalamsaifi.spring.playground.service;

import com.saalamsaifi.spring.playground.model.Request;
import com.saalamsaifi.spring.playground.model.Response;
import java.io.UnsupportedEncodingException;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

public interface IRequestLogService {

  Request logRequestInfo(ContentCachingRequestWrapper requestWrapper) throws UnsupportedEncodingException;

  Response logResponseInfo(ContentCachingResponseWrapper responseWrapper) throws UnsupportedEncodingException;
}
