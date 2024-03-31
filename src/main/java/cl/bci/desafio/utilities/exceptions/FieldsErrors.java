package cl.bci.desafio.utilities.exceptions;

import java.util.Objects;

public class FieldsErrors {

  private String field;
  private String error;

  public FieldsErrors() {}

  public FieldsErrors(final String field, final String error) {
    this.field = field;
    this.error = error;
  }

  public String getField() {
    return field;
  }

  public String getError() {
    return error;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final FieldsErrors that = (FieldsErrors) o;
    return Objects.equals(getField(), that.getField())
        && Objects.equals(getError(), that.getError());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getField(), getError());
  }
}
