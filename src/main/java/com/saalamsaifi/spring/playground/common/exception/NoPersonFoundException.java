package com.saalamsaifi.spring.playground.common.exception;

public class NoPersonFoundException extends RuntimeException {
  private final String firstName;

  public NoPersonFoundException(String firstName) {
    super("No person found with given firstName!");
    this.firstName = firstName;
  }

  public String getFirstName() {
    return firstName;
  }
}
