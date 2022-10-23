package com.saalamsaifi.spring.playground.interceptor;

import com.saalamsaifi.spring.playground.common.utils.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private final UserDetailsService service;
  private final JwtTokenUtils utils;

  public JwtRequestFilter(@Qualifier("jwt") UserDetailsService service, JwtTokenUtils utils) {
    this.service = service;
    this.utils = utils;
  }

  @Override
  protected void doFilterInternal(
    HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
    final var authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

    String username = null;
    String token = null;

    if (Objects.nonNull(authorization) && authorization.startsWith("Bearer ")) {
      token = authorization.substring(7);
      try {
        username = utils.getUsername(token);
      } catch (IllegalArgumentException exception) {
        System.out.println("Unable to get JWT token");
      } catch (ExpiredJwtException exception) {
        System.out.println("JWT token has expired");
      }
    } else {
      System.out.println("JWT token is invalid. Please validate your Authorization header.");
    }

    if (Objects.nonNull(username)
      && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
      UserDetails details = service.loadUserByUsername(username);

      if (utils.validate(token, details)) {
        var authenticationToken =
          new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }

    filterChain.doFilter(request, response);
  }
}
