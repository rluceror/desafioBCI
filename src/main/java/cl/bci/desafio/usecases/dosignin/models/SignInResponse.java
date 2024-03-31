package cl.bci.desafio.usecases.dosignin.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.util.Objects;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SignInResponse implements Serializable {

  private static final long serialVersionUID = 1511010326660703374L;
  private Long id;
  private String created;
  private String modified;
  private String lastLogin;
  private String token;
  private boolean active;

  public SignInResponse() {}

  public SignInResponse(
      final Long id,
      final String created,
      final String modified,
      final String lastLogin,
      final String token,
      final boolean active) {
    this.id = id;
    this.created = created;
    this.modified = modified;
    this.lastLogin = lastLogin;
    this.token = token;
    this.active = active;
  }

  public Long getId() {
    return id;
  }

  public String getCreated() {
    return created;
  }

  public String getModified() {
    return modified;
  }

  public String getLastLogin() {
    return lastLogin;
  }

  public String getToken() {
    return token;
  }

  public boolean isActive() {
    return active;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final SignInResponse that = (SignInResponse) o;
    return isActive() == that.isActive()
        && Objects.equals(getId(), that.getId())
        && Objects.equals(getCreated(), that.getCreated())
        && Objects.equals(getModified(), that.getModified())
        && Objects.equals(getLastLogin(), that.getLastLogin())
        && Objects.equals(getToken(), that.getToken());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getId(), getCreated(), getModified(), getLastLogin(), getToken(), isActive());
  }

  @Override
  public String toString() {
    return "DoSignInResponse{"
        + "id="
        + getId()
        + ", created='"
        + getCreated()
        + '\''
        + ", modified='"
        + getModified()
        + '\''
        + ", lastLogin='"
        + getLastLogin()
        + '\''
        + ", token='"
        + getToken()
        + '\''
        + ", is_active="
        + isActive()
        + '}';
  }
}
