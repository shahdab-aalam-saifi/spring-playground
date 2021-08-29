package com.saalamsaifi.spring.playground.service.impl;

import com.saalamsaifi.spring.playground.common.utils.RequestLogUtils;
import com.saalamsaifi.spring.playground.model.Request;
import com.saalamsaifi.spring.playground.model.Response;
import com.saalamsaifi.spring.playground.service.IRequestLogService;
import org.springframework.stereotype.Service;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@Service
public class RequestLogServiceImpl implements IRequestLogService {
  @Override
  public Request logRequestInfo(ContentCachingRequestWrapper wrapper)
    throws UnsupportedEncodingException {

    //// Request Headers
    var allowedHeaders = RequestLogUtils.getRequestHeaders();
    var headers = new HashMap<String, String>();

    for (String key : allowedHeaders) {
      headers.put(key, wrapper.getHeader(key));
    }

    //// Request Parameters
    var allowedParams = RequestLogUtils.getRequestParams();
    var params = new HashMap<String, String>();

    for (String key : allowedParams) {
      params.put(key, wrapper.getParameter(key));
    }

    var request = new Request();

    request.setHeaders(headers);
    request.setParameters(params);

    return request;
  }

  @Override
  public Response logResponseInfo(ContentCachingResponseWrapper wrapper)
    throws UnsupportedEncodingException {
    //// Response Headers
    var allowedHeaders = RequestLogUtils.getResponseHeaders();
    var headers = new HashMap<String, String>();

    for (String key : allowedHeaders) {
      headers.put(key, wrapper.getHeader(key));
    }

    var response = new Response();

    response.setStatus(wrapper.getStatus());
    response.setHeaders(headers);

    return response;
  }
}
