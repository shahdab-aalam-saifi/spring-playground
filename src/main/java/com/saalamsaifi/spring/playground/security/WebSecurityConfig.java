package com.saalamsaifi.spring.playground.security;

import com.saalamsaifi.spring.playground.interceptor.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final JwtAuthenticationEntryPoint entryPoint;
  private final UserDetailsService service;
  private final JwtRequestFilter filter;

  public WebSecurityConfig(JwtAuthenticationEntryPoint entryPoint, @Qualifier("jwt") UserDetailsService service, JwtRequestFilter filter) {
    this.entryPoint = entryPoint;
    this.service = service;
    this.filter = filter;
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
    builder.userDetailsService(service).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
          .antMatchers("/v1/secure/authenticate").permitAll()
          .anyRequest().authenticated()
      .and()
        .exceptionHandling().authenticationEntryPoint(entryPoint)
      .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
  }
}
