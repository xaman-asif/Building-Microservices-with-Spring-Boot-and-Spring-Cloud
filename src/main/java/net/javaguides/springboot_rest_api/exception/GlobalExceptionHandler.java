package net.javaguides.springboot_rest_api.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                      WebRequest webRequest) {
    ErrorDetails errorDetails = new ErrorDetails(
        LocalDateTime.now(),
        exception.getMessage(),
        webRequest.getDescription(false),
        "USER_NOT_FOUND"
    );

    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<ErrorDetails> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception,
                                                                        WebRequest webRequest) {
    ErrorDetails errorDetails = new ErrorDetails(
        LocalDateTime.now(),
        exception.getMessage(),
        webRequest.getDescription(false),
        "EMAIL_ALREADY_EXISTS"
    );

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetails> handleOtherException(Exception exception,
                                                           WebRequest webRequest) {
    ErrorDetails errorDetails = new ErrorDetails(
        LocalDateTime.now(),
        exception.getMessage(),
        webRequest.getDescription(false),
        "INTERNAL_SERVER_ERROR"
    );

    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers, HttpStatusCode status,
                                                                WebRequest request) {
    Map<String, String> errors = new HashMap<>();
    List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

    errorList.forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.merge(fieldName, errorMessage, (existing, newMsg) -> existing + ", " + newMsg);
    });

    ErrorDetails errorDetails = new ErrorDetails(
        LocalDateTime.now(),
        errors,
        request.getDescription(false),
        "VALIDATION_ERROR"
    );

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }
}
