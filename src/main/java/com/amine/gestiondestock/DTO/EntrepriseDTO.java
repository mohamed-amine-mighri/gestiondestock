package com.amine.gestiondestock.DTO;

import java.math.BigDecimal;
import java.util.List;

import com.amine.gestiondestock.model.Entreprise;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Builder
@Data
@Slf4j
public class EntrepriseDTO {

	  private Integer id;

	  private String nom;

	  private String description;

	  private String adresse;

	  private String photo;

	  private String email;

	  private String numtelephone;

	  private String siteWeb;

	  @JsonIgnore
	  private List<UtilisateurDTO> utilisateurs;
	
	  public static EntrepriseDTO fromEntity(Entreprise entreprise) {
	    if (entreprise == null) {
	      return null;
	    }
	    return EntrepriseDTO.builder()
	        .id(entreprise.getId())
	        .nom(entreprise.getNom())
	        .description(entreprise.getDescription())
	        .adresse(entreprise.getAdresse())
	        .photo(entreprise.getPhoto())
	        .email(entreprise.getEmail())
	        .numtelephone(entreprise.getNumTelephone())
	        .siteWeb(entreprise.getSiteWeb())
	        .build();
	  }

	  public static Entreprise toEntity(EntrepriseDTO entrepriseDTO) {
		  log.error("the boj",entrepriseDTO);
	    if (entrepriseDTO == null) {
	      return null;
	    }
	    Entreprise entreprise = new Entreprise();
	    entreprise.setId(entrepriseDTO.getId());
	    entreprise.setNom(entrepriseDTO.getNom());
	    entreprise.setDescription(entrepriseDTO.getDescription());
	    entreprise.setAdresse(entrepriseDTO.getAdresse());
	    entreprise.setPhoto(entrepriseDTO.getPhoto());
	    entreprise.setEmail(entrepriseDTO.getEmail());
	    entreprise.setNumTelephone(entrepriseDTO.getNumtelephone());
	    entreprise.setSiteWeb(entrepriseDTO.getSiteWeb());
	    
	    return entreprise;
	  }
	  
}
