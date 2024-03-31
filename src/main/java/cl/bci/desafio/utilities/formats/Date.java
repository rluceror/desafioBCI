package cl.bci.desafio.utilities.formats;

import cl.bci.desafio.utilities.AppConstant;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public final class Date {

  public static String dateNow() {
    return DateTimeFormatter.ofPattern(AppConstant.DATE_PATTERN)
        .withZone(ZoneOffset.UTC)
        .format(LocalDateTime.now());
  }

  public Date() {}
}
