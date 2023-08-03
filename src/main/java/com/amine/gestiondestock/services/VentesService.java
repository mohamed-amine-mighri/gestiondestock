package com.amine.gestiondestock.services;

import com.amine.gestiondestock.DTO.VentesDTO;
import java.util.List;

public interface VentesService {

  VentesDTO save(VentesDTO dto);

  VentesDTO findById(Integer id);

  VentesDTO findByCode(String code);

  List<VentesDTO> findAll();

  void delete(Integer id);

}
