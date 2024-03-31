package cl.bci.desafio.usecases.dosignin;

import cl.bci.desafio.data.BciRepository;
import cl.bci.desafio.data.dtos.User;
import cl.bci.desafio.usecases.dosignin.models.SignInRequest;
import cl.bci.desafio.usecases.dosignin.models.SignInResponse;
import cl.bci.desafio.utilities.jwt.JwtTokenProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cl.bci.desafio.utilities.AppConstant.ROL_ADMIN;
import static cl.bci.desafio.utilities.AppConstant.ROL_USER;
import static cl.bci.desafio.utilities.formats.Date.dateNow;

@Service
public class SignInUseCase {

  // region fields
  private final Logger logsDoSignUpUseCase;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  private final BciRepository bciRepository;
  private final PasswordEncoder passwordEncoder;
  // endregion

  @Autowired
  public SignInUseCase(
      final AuthenticationManager authenticationManager,
      final JwtTokenProvider jwtTokenProvider,
      final BciRepository bciRepository,
      final PasswordEncoder passwordEncoder) {
    this.logsDoSignUpUseCase = LogManager.getLogger(SignInUseCase.class);
    this.authenticationManager = authenticationManager;
    this.jwtTokenProvider = jwtTokenProvider;
    this.bciRepository = bciRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public SignInResponse doSignIn(final SignInRequest signInRequest) {
    // EncryptPassword
    final SignInRequest encryptSignInRequest =
        new SignInRequest(
            signInRequest.getName(),
            signInRequest.getEmail(),
            passwordEncoder.encode(signInRequest.getPassword()),
            signInRequest.getPhones());
    // Do Log Class
    logsDoSignUpUseCase.info(
        "Estoy aca: Class:SignInUseCase, Method: SignIn, Message: {}", encryptSignInRequest);
    // Do find email user
    User user = bciRepository.findByEmail(signInRequest.getEmail());
    // Do create token user
    final List<String> listRoles = new ArrayList<>();
    listRoles.add(ROL_ADMIN);
    listRoles.add(ROL_USER);
    //
    final String token = jwtTokenProvider.createToken(signInRequest.getEmail(), listRoles);
    //
    if (user == null) {
      // Do Register User
      user =
          bciRepository.save(
              new User(
                  signInRequest.getName(),
                  signInRequest.getEmail(),
                  encryptSignInRequest.getPassword(),
                  signInRequest.getPhones(),
                  dateNow(),
                  dateNow(),
                  dateNow(),
                  token,
                  listRoles,
                  true));
    }
    //
    if (user.getToken() != null) {
      // Do Log Action
      logsDoSignUpUseCase.info(
          "Estoy aca: Class:SignInUseCase, Method: SignIn, Action: Create User, Message: {}",
          user);
      // Do authenticate user
      final Authentication doAuthentication =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                  user.getEmail(), signInRequest.getPassword()));
      // Do log authenticate user
      logsDoSignUpUseCase.info(
          "Estoy aca: Class:SignInUseCase, Method: SignIn, Action: user authenticate, Message: {}",
          doAuthentication.getAuthorities());
      // Do log create token user
      logsDoSignUpUseCase.info(
          "Estoy aca: Class:SignInUseCase, Method: SignIn, Action: create token user");
      // Do create dateNow
      final String dateNow = dateNow();
      // Do update lastLogin data to user
      final int updateUser =
          bciRepository.updateUser(user.getEmail(), token, dateNow, dateNow);
      // Do log update user
      logsDoSignUpUseCase.info(
          "Estoy aca: Class:SignInUseCase, Method: SignIn, Action: update user, Message: {}",
          updateUser);
    }
    // Do create response sign in user
    final SignInResponse signInResponse =
        new SignInResponse(
            user.getId(),
            user.getCreated(),
            user.getModified(),
            user.getLastLogin(),
            user.getToken(),
            user.isActive());
    // Do log response sign in user
    logsDoSignUpUseCase.info(
        "Estoy aca: Class:SignInUseCase, Method: SignIn, Action: create response, Message: {}",
            signInResponse);
    // create token persistence
    // return response
    return signInResponse;
  }
}
