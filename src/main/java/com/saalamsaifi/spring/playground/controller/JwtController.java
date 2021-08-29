package com.saalamsaifi.spring.playground.controller;

import com.saalamsaifi.spring.playground.common.utils.JwtTokenUtils;
import com.saalamsaifi.spring.playground.request.JwtRequest;
import com.saalamsaifi.spring.playground.response.JwtResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/v1/secure")
public class JwtController {
  private final AuthenticationManager manager;
  private final UserDetailsService service;
  private final JwtTokenUtils utils;

  public JwtController(
      AuthenticationManager manager,
      @Qualifier("jwt") UserDetailsService service,
      JwtTokenUtils utils) {
    this.manager = manager;
    this.service = service;
    this.utils = utils;
  }

  @PostMapping("/authenticate")
  public ResponseEntity<JwtResponse> createToken(@RequestBody JwtRequest request) throws Exception {
    authenticate(request.getUsername(), request.getPassword());

    final var details = service.loadUserByUsername(request.getUsername());
    final var token = utils.generate(details);

    return ResponseEntity.ok(new JwtResponse(token));
  }

  @GetMapping("/sayHello")
  public ResponseEntity<String> getSayHello(Principal principal) {
    var username = "User";

    if (principal instanceof UsernamePasswordAuthenticationToken) {
      var tokenPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
      if (tokenPrincipal instanceof User) {
        username = ((User) tokenPrincipal).getUsername();
      }
    }

    return ResponseEntity.ok("Hello, " + username);
  }

  private void authenticate(String username, String password) throws Exception {
    try {
      manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException exception) {
      throw new Exception("USER_DISABLED", exception);
    } catch (BadCredentialsException exception) {
      throw new Exception("INVALID_CREDENTIALS", exception);
    }
  }
}
