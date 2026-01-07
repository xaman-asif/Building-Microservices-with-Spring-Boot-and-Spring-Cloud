package net.javaguides.springboot_rest_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException {
  private final String message;

  public EmailAlreadyExistsException(String message) {
    super(String.format("Email already exists with %s", message));
    this.message = message;
  }
}
