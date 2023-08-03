package com.amine.gestiondestock.services;

import com.amine.gestiondestock.DTO.FournisseurDTO;
import java.util.List;

public interface FournisseurService {

  FournisseurDTO save(FournisseurDTO
                              dto);

  FournisseurDTO findById(Integer id);

  List<FournisseurDTO> findAll();

  void delete(Integer id);

}
