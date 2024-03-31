package cl.bci.desafio.utilities.jwt.exceptions;

import cl.bci.desafio.utilities.AppConstant;
import cl.bci.desafio.utilities.formats.HandlerMessage;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public class CustomAccessDeniedHandler implements AccessDeniedHandler, Serializable {

  private static final long serialVersionUID = -6859297070553441327L;

  public CustomAccessDeniedHandler() {}

  @Override
  public void handle(
      HttpServletRequest request, HttpServletResponse response, AccessDeniedException arg2)
      throws IOException {
    HandlerMessage.formatMessageException(
        response, HttpServletResponse.SC_UNAUTHORIZED, AppConstant.ACCESS_DENIED_EXCEPTION_MESSAGE);
  }
}
