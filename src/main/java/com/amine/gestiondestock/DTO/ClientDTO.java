package com.amine.gestiondestock.DTO;


import java.math.BigDecimal;
import java.util.List;

import com.amine.gestiondestock.model.Client;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ClientDTO {

	 private Integer id;

	 private String nom;

	 private String prenom;

	 private String adresse;

	 private String photo;

	 private String email;

	 private String numtelephone;

	 private Integer idEntreprise;
	 
	 private List<CommandeClientDTO> commandeClients;
	 
	 public static ClientDTO fromEntity(Client client) {
		    if (client == null) {
		      return null;
		    }
		    return ClientDTO.builder()
		        .id(client.getId())
		        .nom(client.getNom())
		        .prenom(client.getPrenom())
		        .adresse(client.getAdresse())
		        .photo(client.getPhoto())
		        .email(client.getEmail())
		        .numtelephone(client.getNumTelephone())
		        .idEntreprise(client.getIdEntreprise())
		        .build();
		  }
	 
	  // Client --> ClientDTO
	  
	  public static Client toEntity(ClientDTO clientDTO) {
	    if (clientDTO == null) {
	      return null;
	    }
	    Client client = new Client();
	    client.setId(clientDTO.getId());
	    client.setNom(clientDTO.getNom());
	    client.setPrenom(clientDTO.getPrenom());
	    client.setAdresse(clientDTO.getAdresse());
	    client.setPhoto(clientDTO.getPhoto());
	    client.setEmail(clientDTO.getEmail());
	    client.setNumTelephone(clientDTO.getNumtelephone());
	    client.setIdEntreprise(clientDTO.getIdEntreprise());
	    return client;
	  }
	 
	
}
