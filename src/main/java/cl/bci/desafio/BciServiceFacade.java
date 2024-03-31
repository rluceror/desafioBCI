package cl.bci.desafio;

import cl.bci.desafio.usecases.dosignin.SignInUseCase;
import cl.bci.desafio.usecases.dosignin.models.SignInRequest;
import cl.bci.desafio.usecases.dosignin.models.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BciServiceFacade implements BciLogicService {


  private final SignInUseCase signInUseCase;

  @Autowired
  public BciServiceFacade(
      final SignInUseCase signInUseCase
      ) {
    this.signInUseCase = signInUseCase;

  }

  @Override
  public SignInResponse doSignIn(final SignInRequest signInRequest) {

    return signInUseCase.doSignIn(signInRequest);
  }

}
