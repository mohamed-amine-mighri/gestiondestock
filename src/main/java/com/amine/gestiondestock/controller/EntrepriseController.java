package com.amine.gestiondestock.controller;

import com.amine.gestiondestock.DTO.EntrepriseDTO;
import com.amine.gestiondestock.controller.API.EntrepriseApi;

import java.util.List;

import com.amine.gestiondestock.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntrepriseController implements EntrepriseApi {

  private EntrepriseService entrepriseService;

  @Autowired
  public EntrepriseController(EntrepriseService entrepriseService) {
    this.entrepriseService = entrepriseService;
  }

  @Override
  public EntrepriseDTO save(EntrepriseDTO dto) {
    return entrepriseService.save(dto);
  }

  @Override
  public EntrepriseDTO findById(Integer id) {
    return entrepriseService.findById(id);
  }

  @Override
  public List<EntrepriseDTO> findAll() {
    return entrepriseService.findAll();
  }

  @Override
  public void delete(Integer id) {
    entrepriseService.delete(id);
  }
}
