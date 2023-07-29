package com.amine.gestiondestock.controller;


import java.util.List;

import com.amine.gestiondestock.DTO.UtilisateurDTO;
import com.amine.gestiondestock.controller.API.UtilisateurApi;
import com.amine.gestiondestock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilisateurController implements UtilisateurApi {

  private UtilisateurService utilisateurService;

  @Autowired
  public UtilisateurController(UtilisateurService utilisateurService) {
    this.utilisateurService = utilisateurService;
  }

  @Override
  public UtilisateurDTO save(UtilisateurDTO dto) {
    return utilisateurService.save(dto);
  }


  @Override
  public UtilisateurDTO findById(Integer id) {
    return utilisateurService.findById(id);
  }

  @Override
  public UtilisateurDTO findByEmail(String email) {
    return utilisateurService.findByEmail(email);
  }

  @Override
  public List<UtilisateurDTO> findAll() {
    return utilisateurService.findAll();
  }

  @Override
  public void delete(Integer id) {
    utilisateurService.delete(id);
  }
}
