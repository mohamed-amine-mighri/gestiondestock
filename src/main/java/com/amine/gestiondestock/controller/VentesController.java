package com.amine.gestiondestock.controller;


import com.amine.gestiondestock.DTO.VentesDTO;
import com.amine.gestiondestock.controller.API.VentesApi;
import com.amine.gestiondestock.services.VentesService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentesController implements VentesApi {

  private VentesService ventesService;

  @Autowired
  public VentesController(VentesService ventesService) {
    this.ventesService = ventesService;
  }

  @Override
  public VentesDTO save(VentesDTO dto) {
    return ventesService.save(dto);
  }

  @Override
  public VentesDTO findById(Integer id) {
    return ventesService.findById(id);
  }

  @Override
  public VentesDTO findByCode(String code) {
    return ventesService.findByCode(code);
  }

  @Override
  public List<VentesDTO> findAll() {
    return ventesService.findAll();
  }

  @Override
  public void delete(Integer id) {
    ventesService.delete(id);
  }
}
