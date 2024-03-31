package cl.bci.desafio.usecases.dosignin.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

import static cl.bci.desafio.utilities.AppConstant.CITYWIDE_EMPTY_ERROR_MESSAGE;
import static cl.bci.desafio.utilities.AppConstant.CITYWIDE_NULL_ERROR_MESSAGE;
import static cl.bci.desafio.utilities.AppConstant.COUNTRYSIDE_EMPTY_ERROR_MESSAGE;
import static cl.bci.desafio.utilities.AppConstant.COUNTRYSIDE_NULL_ERROR_MESSAGE;
import static cl.bci.desafio.utilities.AppConstant.NUMBER_EMPTY_ERROR_MESSAGE;
import static cl.bci.desafio.utilities.AppConstant.NUMBER_NULL_ERROR_MESSAGE;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Phone implements Serializable {

  private static final long serialVersionUID = -2989644983507113348L;

  @NotNull(message = NUMBER_NULL_ERROR_MESSAGE)
  @NotBlank(message = NUMBER_EMPTY_ERROR_MESSAGE)
  private String number;

  @NotNull(message = CITYWIDE_NULL_ERROR_MESSAGE)
  @NotBlank(message = CITYWIDE_EMPTY_ERROR_MESSAGE)
  private String cityCode;

  @NotNull(message = COUNTRYSIDE_NULL_ERROR_MESSAGE)
  @NotBlank(message = COUNTRYSIDE_EMPTY_ERROR_MESSAGE)
  private String countryCode;

  public Phone() {}

  public Phone(final String number, final String cityCode, final String countryCode) {
    this.number = number;
    this.cityCode = cityCode;
    this.countryCode = countryCode;
  }

  public String getNumber() {
    return number;
  }

  public String getCityCode() {
    return cityCode;
  }

  public String getCountryCode() {
    return countryCode;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Phone phone = (Phone) o;
    return Objects.equals(getNumber(), phone.getNumber())
        && Objects.equals(getCityCode(), phone.getCityCode())
        && Objects.equals(getCountryCode(), phone.getCountryCode());
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, cityCode, countryCode);
  }

  @Override
  public String toString() {
    return "Phone{"
        + "number='"
        + getNumber()
        + '\''
        + ", cityCode='"
        + getCityCode()
        + '\''
        + ", countryCode='"
        + getCountryCode()
        + '\''
        + '}';
  }
}
