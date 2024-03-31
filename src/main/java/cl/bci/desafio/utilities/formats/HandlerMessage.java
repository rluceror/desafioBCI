package cl.bci.desafio.utilities.formats;

import cl.bci.desafio.utilities.AppConstant;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HandlerMessage {

  public HandlerMessage() {}

  public static void formatMessageException(
      final HttpServletResponse response, final int handlerStatus, final String handlerMeessage)
      throws IOException {

    String json = String.format(AppConstant.FORMAT_MESSAGE_JWT, handlerMeessage);
    response.setStatus(handlerStatus);
    response.setContentType(AppConstant.TYPE_MESSAGE_JWT);
    response.setCharacterEncoding(AppConstant.CHARACTER_ENCODING_MESSAGE_JWT);
    response.getWriter().write(json);
  }

}
