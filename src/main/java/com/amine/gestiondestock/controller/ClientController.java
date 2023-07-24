package com.amine.gestiondestock.controller;



import java.util.List;

import com.amine.gestiondestock.DTO.ClientDTO;
import com.amine.gestiondestock.controller.API.ClientApi;
import com.amine.gestiondestock.services.ClientSev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController implements ClientApi {

  private ClientSev clientService;

  @Autowired
  public ClientController(ClientSev clientService) {
    this.clientService = clientService;
  }

  @Override
  public ClientDTO save(ClientDTO dto) {
    return clientService.save(dto);
  }

  @Override
  public ClientDTO findById(Integer id) {
    return clientService.findById(id);
  }

  @Override
  public List<ClientDTO> findAll() {
    return clientService.findAll();
  }

  @Override
  public void delete(Integer id) {
    clientService.delete(id);
  }
}
