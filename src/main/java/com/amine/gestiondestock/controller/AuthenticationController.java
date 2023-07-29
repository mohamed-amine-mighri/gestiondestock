package com.amine.gestiondestock.controller;



import com.amine.gestiondestock.DTO.auth.AuthenticationRequest;
import com.amine.gestiondestock.DTO.auth.AuthenticationResponse;
import com.amine.gestiondestock.controller.API.AuthenticationApi;
import com.amine.gestiondestock.model.auth.ExtendedUser;
import com.amine.gestiondestock.services.auth.ApplicationUserDetailsService;
import com.amine.gestiondestock.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements AuthenticationApi {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private ApplicationUserDetailsService userDetailsService;

  @Autowired
  private JwtUtil jwtUtil;

  @Override
  public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getLogin(),
                    request.getPassword()
            )
    );
    final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());

    final String jwt = jwtUtil.generateToken((ExtendedUser) userDetails);

    return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
  }

}
