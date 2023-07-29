package com.amine.gestiondestock.controller.API;


import static com.amine.gestiondestock.utils.Constants.UTILISATEUR_ENDPOINT;


import java.util.List;

import com.amine.gestiondestock.DTO.UtilisateurDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UtilisateurApi {

  @PostMapping(UTILISATEUR_ENDPOINT + "/create")
  UtilisateurDTO save(@RequestBody UtilisateurDTO dto);

  @GetMapping(UTILISATEUR_ENDPOINT + "/{idUtilisateur}")
  UtilisateurDTO findById(@PathVariable("idUtilisateur") Integer id);

  @GetMapping(UTILISATEUR_ENDPOINT + "/find/{email}")
  UtilisateurDTO findByEmail(@PathVariable("email") String email);

  @GetMapping(UTILISATEUR_ENDPOINT + "/all")
  List<UtilisateurDTO> findAll();

  @DeleteMapping(UTILISATEUR_ENDPOINT + "/delete/{idUtilisateur}")
  void delete(@PathVariable("idUtilisateur") Integer id);

}
