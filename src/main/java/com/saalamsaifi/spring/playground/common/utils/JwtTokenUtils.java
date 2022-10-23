package com.saalamsaifi.spring.playground.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtils implements Serializable {

  public static final long TOKEN_VALIDITY = 5L * 60 * 60;

  @Value("$jwt.secret")
  private String secret;

  public String getUsername(String token) {
    return getClaim(token, Claims::getSubject);
  }

  public Date getExpiration(String token) {
    return getClaim(token, Claims::getExpiration);
  }

  public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
    final var claims = getAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generate(UserDetails details) {
    return doGenerateToken(new HashMap<>(), details.getUsername());
  }

  private String doGenerateToken(Map<String, Object> claims, String subject) {
    claims.put("username", subject);
    claims.put("ClientId", Base64.getEncoder().encodeToString(subject.getBytes()));

    return Jwts.builder()
      .setClaims(claims)
      .setSubject(subject)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
      .signWith(SignatureAlgorithm.HS512, secret)
      .compact();
  }

  public boolean validate(String token, UserDetails details) {
    final var username = getUsername(token);
    return username.equals(details.getUsername()) && !isExpired(token);
  }

  private Claims getAllClaims(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  private boolean isExpired(String token) {
    return getExpiration(token).before(new Date());
  }
}
