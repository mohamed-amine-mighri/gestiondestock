package com.amine.gestiondestock.DTO;


import java.math.BigDecimal;
import java.util.List;

import com.amine.gestiondestock.model.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ClientDTO {

	private Integer id;

	private String nom;

	private String prenom;

	private AdresseDto adresse;

	private String photo;

	private String mail;

	private String numTel;

	private Integer idEntreprise;

	@JsonIgnore
	private List<CommandeClientDTO> commandeClients;

	public static ClientDTO fromEntity(Client client) {
		if (client == null) {
			return null;
		}
		return ClientDTO.builder()
				.id(client.getId())
				.nom(client.getNom())
				.prenom(client.getPrenom())
				.adresse(AdresseDto.fromEntity(client.getAdresse()))
				.photo(client.getPhoto())
				.mail(client.getMail())
				.numTel(client.getNumTel())
				.idEntreprise(client.getIdEntreprise())
				.build();
	}

	public static Client toEntity(ClientDTO dto) {
		if (dto == null) {
			return null;
		}
		Client client = new Client();
		client.setId(dto.getId());
		client.setNom(dto.getNom());
		client.setPrenom(dto.getPrenom());
		client.setAdresse(AdresseDto.toEntity(dto.getAdresse()));
		client.setPhoto(dto.getPhoto());
		client.setMail(dto.getMail());
		client.setNumTel(dto.getNumTel());
		client.setIdEntreprise(dto.getIdEntreprise());
		return client;
	}
	
}
