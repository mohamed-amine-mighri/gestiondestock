package com.amine.gestiondestock.DTO;

import java.math.BigDecimal;
import java.util.List;

import com.amine.gestiondestock.model.Fournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class FournisseurDTO {

	private Integer id;

	private String nom;

	private String prenom;

	private AdresseDto adresse;

	private String photo;

	private String mail;

	private String numTel;

	private Integer idEntreprise;

	@JsonIgnore
	private List<CommandeFournisseurDTO> commandeFournisseurs;

	public static FournisseurDTO fromEntity(Fournisseur fournisseur) {
		if (fournisseur == null) {
			return null;
		}
		return FournisseurDTO.builder()
				.id(fournisseur.getId())
				.nom(fournisseur.getNom())
				.prenom(fournisseur.getPrenom())
				.adresse(AdresseDto.fromEntity(fournisseur.getAdresse()))
				.photo(fournisseur.getPhoto())
				.mail(fournisseur.getMail())
				.numTel(fournisseur.getNumTel())
				.idEntreprise(fournisseur.getIdEntreprise())
				.build();
	}

	public static Fournisseur toEntity(FournisseurDTO dto) {
		if (dto == null) {
			return null;
		}
		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setId(dto.getId());
		fournisseur.setNom(dto.getNom());
		fournisseur.setPrenom(dto.getPrenom());
		fournisseur.setAdresse(AdresseDto.toEntity(dto.getAdresse()));
		fournisseur.setPhoto(dto.getPhoto());
		fournisseur.setMail(dto.getMail());
		fournisseur.setNumTel(dto.getNumTel());
		fournisseur.setIdEntreprise(dto.getIdEntreprise());

		return fournisseur;
	}
	
}
