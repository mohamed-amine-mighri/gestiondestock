package com.amine.gestiondestock.services;

import java.util.List;

import com.amine.gestiondestock.DTO.ClientDTO;

public interface ClientSev {

	ClientDTO save(ClientDTO dto);
	ClientDTO findById(Integer id);
	List<ClientDTO> findAll();
	void delete(Integer id);
	
}
