package com.amine.gestiondestock.services;

import com.amine.gestiondestock.DTO.CategorieDTO;

import java.util.List;


public interface CathegorieServ {

	 CategorieDTO save(CategorieDTO dto);

	 CategorieDTO findById(Integer id);

	 List<CategorieDTO> findAll();

	 void delete(Integer id);
	
}
