package cl.bci.desafio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/protected")
public class BciProtectedController {

  private final BciServiceFacade bciServiceFacade;

  @Autowired
  public BciProtectedController(final BciServiceFacade bciServiceFacade) {
    this.bciServiceFacade = bciServiceFacade;
  }

}
