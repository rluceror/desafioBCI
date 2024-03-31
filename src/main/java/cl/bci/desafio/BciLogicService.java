package cl.bci.desafio;

import cl.bci.desafio.usecases.dosignin.models.SignInRequest;
import cl.bci.desafio.usecases.dosignin.models.SignInResponse;

public interface BciLogicService {

  SignInResponse doSignIn(SignInRequest signInRequest);

}
