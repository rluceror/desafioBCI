package cl.bci.desafio.utilities.jwt;

import cl.bci.desafio.data.BciRepository;
import cl.bci.desafio.data.dtos.User;
import cl.bci.desafio.utilities.jwt.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

  private BciRepository users;

  public CustomUserDetailsService(BciRepository users) {
    this.users = users;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    CustomUserDetails customUserDetails = null;

    final User user = users.findByEmail(username);
    if (user != null) {
      customUserDetails =
          new CustomUserDetails(user.getEmail(), user.getPassword(), user.getRoles());
    }

    return customUserDetails;
  }
}
