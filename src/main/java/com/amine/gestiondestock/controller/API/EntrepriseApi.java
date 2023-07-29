package com.amine.gestiondestock.controller.API;

import java.util.List;

import com.amine.gestiondestock.DTO.EntrepriseDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static com.amine.gestiondestock.utils.Constants.ENTREPRISE_ENDPOINT;

public interface EntrepriseApi {

  @PostMapping( ENTREPRISE_ENDPOINT + "/create")
  EntrepriseDTO save(@RequestBody EntrepriseDTO dto);

  @GetMapping(ENTREPRISE_ENDPOINT +  "/{idEntreprise}")
  EntrepriseDTO findById(@PathVariable("idEntreprise") Integer id);

  @GetMapping(ENTREPRISE_ENDPOINT + "/all")
  List<EntrepriseDTO> findAll();

  @DeleteMapping(ENTREPRISE_ENDPOINT + "/delete/{idEntreprise}")
  void delete(@PathVariable("idEntreprise") Integer id);

}
