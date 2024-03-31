package cl.bci.desafio.utilities.jwt.exceptions;

import cl.bci.desafio.utilities.AppConstant;
import cl.bci.desafio.utilities.formats.HandlerMessage;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint, Serializable {

  private static final long serialVersionUID = -9206078375353792395L;

  public AuthenticationEntryPointHandler() {}

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException {
    HandlerMessage.formatMessageException(response, HttpServletResponse.SC_BAD_REQUEST, AppConstant.SESSION_EXCEPTION_MESSAGE);
  }
}
