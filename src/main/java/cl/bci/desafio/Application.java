package cl.bci.desafio;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan
public class Application {

  public static void main(String[] args) throws Throwable {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(16);
  }

  @Bean
  public AuthenticationFailureHandler failureHandler() {
    return (request, response, ex) -> {
      throw ex;
    };
  }
}
