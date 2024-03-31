package cl.bci.desafio;

import cl.bci.desafio.usecases.dosignin.models.SignInRequest;
import cl.bci.desafio.usecases.dosignin.models.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/desafioBci")
public class BciController {


  private final BciServiceFacade bciServiceFacade;

  @Autowired
  public BciController(final BciServiceFacade bciServiceFacade) {
    this.bciServiceFacade = bciServiceFacade;
  }

  @RequestMapping(
      value = "/doSignIn",
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public SignInResponse doSignIn(@Valid @RequestBody final SignInRequest signInRequest) {

    return bciServiceFacade.doSignIn(signInRequest);
  }
}
