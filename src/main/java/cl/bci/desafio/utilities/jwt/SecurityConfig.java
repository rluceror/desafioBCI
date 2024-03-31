package cl.bci.desafio.utilities.jwt;

import cl.bci.desafio.data.BciRepository;
import cl.bci.desafio.utilities.AppConstant;
import cl.bci.desafio.utilities.jwt.exceptions.AuthenticationEntryPointHandler;
import cl.bci.desafio.utilities.jwt.exceptions.CustomAccessDeniedHandler;

import cl.bci.desafio.utilities.jwt.exceptions.CustomLogoutHandler;
import cl.bci.desafio.utilities.jwt.exceptions.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired JwtTokenProvider jwtTokenProvider;
  @Autowired
  BciRepository bciRepository;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic()
        .disable()
        .csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(AppConstant.API_REST_UNPROTECTED_DOSIGNIN)
        .permitAll()
        .antMatchers(AppConstant.API_REST_PROTECTED_GETUSERS)
        .hasAnyRole(AppConstant.HAS_ROL_USER, AppConstant.HAS_ROL_ADMIN)
        .antMatchers(AppConstant.API_REST_PROTECTED_FIND_USER_BY_EMAIL)
        .hasAnyRole(AppConstant.HAS_ROL_USER, AppConstant.HAS_ROL_ADMIN)
        .anyRequest()
        .authenticated()
        .and()
        .apply(new JwtConfigurer(jwtTokenProvider))
        .and()
        .logout()
        .logoutRequestMatcher(
            new AntPathRequestMatcher(
                AppConstant.API_REST_PROTECTED_DOSIGNOUT, RequestMethod.POST.toString(), true))
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .deleteCookies("SESSION")
        .deleteCookies("JSESSIONID")
        .addLogoutHandler(new CustomLogoutHandler(jwtTokenProvider, bciRepository))
        .logoutSuccessHandler(new CustomLogoutSuccessHandler())
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(new AuthenticationEntryPointHandler())
        .accessDeniedHandler(new CustomAccessDeniedHandler());
  }
}
