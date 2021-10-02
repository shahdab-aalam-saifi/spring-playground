package com.saalamsaifi.spring.playground.interceptor;

import com.saalamsaifi.spring.playground.model.LogRequest;
import com.saalamsaifi.spring.playground.service.IClientInfoExtractService;
import com.saalamsaifi.spring.playground.service.IRequestLogService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestLogFilter extends OncePerRequestFilter {
  private final IRequestLogService requestService;
  private final IClientInfoExtractService clientInfoService;

  public RequestLogFilter(
      IRequestLogService requestService, IClientInfoExtractService clientInfoService) {
    this.requestService = requestService;
    this.clientInfoService = clientInfoService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    var requestWrapper = new ContentCachingRequestWrapper(request);
    var responseWrapper = new ContentCachingResponseWrapper(response);

    filterChain.doFilter(requestWrapper, responseWrapper);

    var clientRequest = requestService.logRequestInfo(requestWrapper);
    var clientResponse = requestService.logResponseInfo(responseWrapper);

    var clientId = clientInfoService.getClientId(request.getHeader(HttpHeaders.AUTHORIZATION));

    var logRequest = new LogRequest();

    logRequest.setUrl(request.getRequestURI());
    logRequest.setClientId(clientId);
    logRequest.setRequest(clientRequest);
    logRequest.setResponse(clientResponse);

    responseWrapper.copyBodyToResponse();
  }
}
