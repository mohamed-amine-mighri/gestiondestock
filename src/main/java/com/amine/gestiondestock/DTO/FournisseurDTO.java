package com.amine.gestiondestock.DTO;

import java.math.BigDecimal;
import java.util.List;

import com.amine.gestiondestock.model.Fournisseur;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class FournisseurDTO {

	 private Integer id;

	  private String nom;

	  private String prenom;

	  private String adresse;

	  private String photo;

	  private String email;

	  private String numtelephone;

	  private Integer idEntreprise;

	  private List<CommandeFournisseurDTO> commandeFournisseurs;
	  
	  public static FournisseurDTO fromEntity(Fournisseur fournisseur) {
		    if (fournisseur == null) {
		      return null;
		    }
		    return FournisseurDTO.builder()
		        .id(fournisseur.getId())
		        .nom(fournisseur.getNom())
		        .prenom(fournisseur.getPrenom())
		        .adresse(fournisseur.getAdresse())
		        .photo(fournisseur.getPhoto())
		        .email(fournisseur.getEmail())
		        .numtelephone(fournisseur.getNumTelephone())
		        .idEntreprise(fournisseur.getIdEntreprise())
		        .build();
		  }

		  public static Fournisseur toEntity(FournisseurDTO fournissurDTO) {
		    if (fournissurDTO == null) {
		      return null;
		    }
		    Fournisseur fournisseur = new Fournisseur();
		    fournisseur.setId(fournissurDTO.getId());
		    fournisseur.setNom(fournissurDTO.getNom());
		    fournisseur.setPrenom(fournissurDTO.getPrenom());
		    fournisseur.setAdresse(fournissurDTO.getAdresse());
		    fournisseur.setPhoto(fournissurDTO.getPhoto());
		    fournisseur.setEmail(fournissurDTO.getEmail());
		    fournisseur.setNumTelephone(fournissurDTO.getNumtelephone());
		    fournisseur.setIdEntreprise(fournissurDTO.getIdEntreprise());

		    return fournisseur;
		  }
	
}
