package cl.bci.desafio.data.dtos;

import cl.bci.desafio.usecases.dosignin.models.Phone;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static cl.bci.desafio.utilities.AppConstant.ROL_ADMIN;
import static cl.bci.desafio.utilities.AppConstant.ROL_USER;

@Entity
@Table(name = "USERS")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
  @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(unique = true, name = "EMAIL")
  private String email;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "PHONES")
  @ElementCollection
  private List<Phone> phones;

  @Column(name = "CREATED")
  private String created;

  @Column(name = "MODIFIED")
  private String modified;

  @Column(name = "LAST_LOGIN")
  private String lastLogin;

  @Column(name = "TOKEN")
  private String token;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> roles = new ArrayList<>();

  @Column(name = "ACTIVE")
  private boolean active;

  public User() {}

  public User(
      final String name,
      final String email,
      final String password,
      final List<Phone> phones,
      final String created,
      final String modified,
      final String lastLogin,
      final String token) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.phones = phones;
    this.created = created;
    this.modified = modified;
    this.lastLogin = lastLogin;
    this.token = token;
    this.active = true;
    this.roles = Arrays.asList(ROL_ADMIN, ROL_USER);
  }

  public User(
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

  public User(
      final Long id,
      final String name,
      final String email,
      final String password,
      final List<Phone> phones,
      final String created,
      final String modified,
      final String lastLogin,
      final String token,
      final List<String> roles,
      final boolean active) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.phones = phones;
    this.created = created;
    this.modified = modified;
    this.lastLogin = lastLogin;
    this.token = token;
    this.roles = roles;
    this.active = active;
  }

  public User(
      final String name,
      final String email,
      final String password,
      final List<Phone> phones,
      final String created,
      final String modified,
      final String lastLogin,
      final String token,
      final List<String> roles,
      final boolean active) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.phones = phones;
    this.created = created;
    this.modified = modified;
    this.lastLogin = lastLogin;
    this.token = token;
    this.roles = roles;
    this.active = active;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public List<Phone> getPhones() {
    return phones;
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

  public List<String> getRoles() {
    return roles;
  }

  public boolean isActive() {
    return active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return isActive() == user.isActive()
        && Objects.equals(getId(), user.getId())
        && Objects.equals(getName(), user.getName())
        && Objects.equals(getEmail(), user.getEmail())
        && Objects.equals(getPassword(), user.getPassword())
        && Objects.equals(getPhones(), user.getPhones())
        && Objects.equals(getCreated(), user.getCreated())
        && Objects.equals(getModified(), user.getModified())
        && Objects.equals(getLastLogin(), user.getLastLogin())
        && Objects.equals(getToken(), user.getToken())
        && Objects.equals(getRoles(), user.getRoles());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getId(),
        getName(),
        getEmail(),
        getPassword(),
        getPhones(),
        getCreated(),
        getModified(),
        getLastLogin(),
        getToken(),
        getRoles(),
        isActive());
  }

  @Override
  public String toString() {
    return "User{"
        + "id="
        + getId()
        + ", name='"
        + getName()
        + '\''
        + ", email='"
        + getEmail()
        + '\''
        + ", password='"
        + getPassword()
        + '\''
        + ", phones='"
        + getPhones()
        + '\''
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
        + ", roles="
        + getRoles()
        + ", is_Active="
        + isActive()
        + '}';
  }
}
