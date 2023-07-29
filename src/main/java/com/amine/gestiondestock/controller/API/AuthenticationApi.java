package com.amine.gestiondestock.controller.API;


import com.amine.gestiondestock.DTO.auth.AuthenticationRequest;
import com.amine.gestiondestock.DTO.auth.AuthenticationResponse;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static com.amine.gestiondestock.utils.Constants.AUTHENTICATION_ENDPOINT;

@Api("Authentications")
public interface AuthenticationApi {

  @PostMapping(AUTHENTICATION_ENDPOINT + "/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);

}
