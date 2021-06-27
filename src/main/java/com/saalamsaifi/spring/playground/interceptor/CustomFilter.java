package com.saalamsaifi.spring.playground.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saalamsaifi.spring.playground.model.LogRequest;
import com.saalamsaifi.spring.playground.model.Request;
import com.saalamsaifi.spring.playground.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
public class CustomFilter extends OncePerRequestFilter {

  @Autowired private ObjectMapper mapper;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    var requestWrapper = new ContentCachingRequestWrapper(request);
    var responseWrapper = new ContentCachingResponseWrapper(response);

    filterChain.doFilter(requestWrapper, responseWrapper);

    // Request Headers
    var requestHeaderNames = requestWrapper.getHeaderNames();
    var requestHeaders = new HashMap<String, String>();

    while (requestHeaderNames.hasMoreElements()) {
      var key = requestHeaderNames.nextElement();
      requestHeaders.put(key, requestWrapper.getHeader(key));
    }

    // Request Parameters
    var requestParamNames = requestWrapper.getParameterNames();
    var requestParams = new HashMap<String, String>();

    while (requestParamNames.hasMoreElements()) {
      var key = requestParamNames.nextElement();
      requestParams.put(key, requestWrapper.getParameter(key));
    }

    // Request Body
    var requestBytes = requestWrapper.getContentAsByteArray();

    var clientRequest = new Request();

    clientRequest.setUrl(requestWrapper.getRequestURL().toString());
    clientRequest.setHeaders(requestHeaders);
    clientRequest.setParameters(requestParams);
    clientRequest.setBody(new String(requestBytes, requestWrapper.getCharacterEncoding()));

    // Response Headers
    var responseHeaderNames = response.getHeaderNames();
    var responseHeaders = new HashMap<String, String>();

    for (String key : responseHeaderNames) {
        responseHeaders.put(key, responseWrapper.getHeader(key));
    }

    // Response Body
    var responseBytes = responseWrapper.getContentAsByteArray();

    var clientResponse = new Response();

    clientResponse.setStatus(responseWrapper.getStatus());
    clientResponse.setHeaders(responseHeaders);
    clientResponse.setBody(new String(responseBytes, responseWrapper.getCharacterEncoding()));

    var logRequest = new LogRequest();

    logRequest.setRequest(clientRequest);
    logRequest.setResponse(clientResponse);

    System.out.println(mapper.writeValueAsString(logRequest));

    responseWrapper.copyBodyToResponse();
  }
}
