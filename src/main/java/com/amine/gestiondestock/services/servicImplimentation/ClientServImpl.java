package com.amine.gestiondestock.services.servicImplimentation;

import java.util.List;
import java.util.stream.Collectors;


import com.amine.gestiondestock.DTO.ClientDTO;
import com.amine.gestiondestock.Exception.EntityNotFoundException;
import com.amine.gestiondestock.Exception.ErrorCodes;
import com.amine.gestiondestock.Exception.InvalidEntityException;
import com.amine.gestiondestock.model.CommandeClient;
import com.amine.gestiondestock.repos.ClientRepos;
import com.amine.gestiondestock.repos.CommandeClientRepos;
import com.amine.gestiondestock.services.ClientSev;
import com.amine.gestiondestock.validator.ClientV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientServImpl implements ClientSev {

	private ClientRepos clientRep;
	private CommandeClientRepos commandeClientRep;
	
	@Autowired
	public ClientServImpl(ClientRepos clientRep, CommandeClientRepos commandeClientRep) {
		this.clientRep = clientRep;
		this.commandeClientRep = commandeClientRep;
	}
	
	public ClientDTO save(ClientDTO dto) {
		List<String> errors = ClientV.validate(dto);
		if (!errors.isEmpty()) {
		   log.error("Client is not valid {}", dto);
		   throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
		}
		return ClientDTO.fromEntity(
	        clientRep.save(
	        	ClientDTO.toEntity(dto)
	        )
			);
		}
	
	
	@Override
	  public ClientDTO findById(Integer id) {
	    if (id == null) {
	      log.error("Client ID is null");
	      return null;
	    }
	    return clientRep.findById(id)
	        .map(ClientDTO::fromEntity)
	        .orElseThrow(() -> new EntityNotFoundException(
	            "Aucun Client avec l'ID = " + id + " n' ete trouve dans la BDD"
	            ,ErrorCodes.CLIENT_NOT_FOUND)
	        );
	  }
	
	
	 @Override
	  public List<ClientDTO> findAll() {
	    return clientRep.findAll().stream()
	        .map(ClientDTO::fromEntity)
	        .collect(Collectors.toList());
	  }
	 
	 
	 @Override
	  public void delete(Integer id) {
	    if (id == null) {
	      log.error("Client ID is null");
	      return;
	    }
	    List<CommandeClient> commandeClients = commandeClientRep.findAllByClientId(id);
	    if (!commandeClients.isEmpty()) {
	      log.error("Impossible de supprimer un client qui a deja des commande clients");
	    }
	    clientRep.deleteById(id);
	  }
	
}
