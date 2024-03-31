package cl.bci.desafio.utilities.exceptions;

import cl.bci.desafio.utilities.AppConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionsHandler extends ResponseEntityExceptionHandler {


  private final Logger logExceptionsHandler;

  @Autowired
  public CustomExceptionsHandler() {
    this.logExceptionsHandler = LogManager.getLogger(CustomExceptionsHandler.class);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    // Create Message Errors
    final List<FieldsErrors> message =
        ex.getBindingResult().getFieldErrors().stream()
            .map(x -> new FieldsErrors(x.getField(), x.getDefaultMessage()))
            .collect(Collectors.toList());
    // Log Errors
    logExceptionsHandler.error(
        "Class:CustomExceptionsHandler, Method: handleMethodArgumentNotValid, Message: {}",
        message);

    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraintViolationException() {

    // Create Message Error
    final String message = AppConstant.CONSTRAINT_EMAIL_VIOLATION_EXCEPTION_MESSAGE;
    // Log Errors
    logExceptionsHandler.error(
        "Class:CustomExceptionsHandler, Method: handleConstraintViolationException, Message: {}",
        message);

    return new ResponseEntity<>(new ExceptionHandlerResponse<>(message), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<Object> handleNullPointerException(final NullPointerException ex) {
    // Log Errors
    logExceptionsHandler.error(
        "Class:CustomExceptionsHandler, Method: handleNullPointerException, Message: {}",
        ex.getMessage());

    return new ResponseEntity<>(
        new ExceptionHandlerResponse<>(ex.getMessage()), HttpStatus.BAD_REQUEST);
  }
}
