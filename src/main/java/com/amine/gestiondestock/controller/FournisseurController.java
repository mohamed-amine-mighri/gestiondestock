package com.amine.gestiondestock.controller;

import com.amine.gestiondestock.DTO.FournisseurDTO;
import com.amine.gestiondestock.services.FournisseurService;
import com.bouali.gestiondestock.controller.api.FournisseurApi;
;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FournisseurController implements FournisseurApi {

  private FournisseurService fournisseurService;

  @Autowired
  public FournisseurController(FournisseurService fournisseurService) {
    this.fournisseurService = fournisseurService;
  }

  @Override
  public FournisseurDTO save(FournisseurDTO dto) {
    return fournisseurService.save(dto);
  }

  @Override
  public FournisseurDTO findById(Integer id) {
    return fournisseurService.findById(id);
  }

  @Override
  public List<FournisseurDTO> findAll() {
    return fournisseurService.findAll();
  }

  @Override
  public void delete(Integer id) {
    fournisseurService.delete(id);
  }
}
