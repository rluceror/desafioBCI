package cl.bci.desafio.utilities.exceptions;

import java.io.Serializable;

public class ExceptionHandlerResponse<T> implements Serializable {

  private static final long serialVersionUID = 3973743650243434150L;
  private transient T message;

  public ExceptionHandlerResponse() {}

  public ExceptionHandlerResponse(T message) {
    this.message = message;
  }

  public T getMessage() {
    return message;
  }
}
