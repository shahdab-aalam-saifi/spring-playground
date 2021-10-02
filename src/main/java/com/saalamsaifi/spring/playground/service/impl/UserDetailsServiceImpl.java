package com.saalamsaifi.spring.playground.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("jwt")
public class UserDetailsServiceImpl implements UserDetailsService {

  private final List<User> users = new ArrayList<>();

  public UserDetailsServiceImpl() {
    // 7lfRlmugo42epHlYeplSW7mufUBe7plw
    users.add(
        new User(
            "ezaniolini0",
            "$2a$10$MKoyhoq3TKU7dccp6zJM5O6aS8Nk0lnCztECB88VZxencdlAUCSKm",
            new ArrayList<>()));
    // CrLq4keWr6chISWu0R686oNlDRu8owRI
    users.add(
        new User(
            "jbartelli2",
            "$2a$10$mKI30vT32exO/tWDkaGhXOcMKljjNWGq7JaTo3AzGMJrtIXnHKNhO",
            new ArrayList<>()));
    // yaqIdefaSwisiDrLtr6thigepribrutl
    users.add(
        new User(
            "hdearnly3",
            "$2a$10$oPLjL7KpgfkHxa05oHWE8OuwvxgctiH/1vWk8OqAYcCXq3XlB3VeC",
            new ArrayList<>()));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = users.stream().filter(u -> u.getUsername().equals(username)).findFirst();

    if (user.isPresent()) {
      return user.get();
    }

    throw new UsernameNotFoundException("User not found with username: " + username);
  }
}
