package cl.bci.desafio.utilities.formats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoadStubs {

  public LoadStubs() {}

  public static String getStubs(final String name) {
    final Class loadStubsClass = LoadStubs.class;
    final InputStream inputStream =
        loadStubsClass.getResourceAsStream(String.format("/fixtures/%s", name));

    return readFromInputStream(inputStream);
  }

  private static String readFromInputStream(final InputStream inputStream) {
    final StringBuilder resultStringBuilder = new StringBuilder();

    try (final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        resultStringBuilder.append(line).append("\n");
      }
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }

    return resultStringBuilder.toString();
  }
}
