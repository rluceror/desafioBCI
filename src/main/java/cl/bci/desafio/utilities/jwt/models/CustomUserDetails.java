package cl.bci.desafio.utilities.jwt.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class CustomUserDetails implements UserDetails {

  private static final long serialVersionUID = 5773413171022681647L;
  private String username;
  private String password;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> roles = new ArrayList<>();

  public CustomUserDetails() {}

  public CustomUserDetails(final String username, final String password, final List<String> roles) {
    this.username = username;
    this.password = password;
    this.roles = roles;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final CustomUserDetails that = (CustomUserDetails) o;
    return Objects.equals(getUsername(), that.getUsername())
        && Objects.equals(getPassword(), that.getPassword())
        && Objects.equals(getRoles(), that.getRoles());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUsername(), getPassword(), getRoles());
  }
}
