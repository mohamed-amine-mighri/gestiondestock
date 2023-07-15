package com.amine.gestiondestock.DTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.amine.gestiondestock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilisateurDTO {

	  private Integer id;

	  private String nom;

	  private String prenom;

	  private String email;

	  //private Instant dateDeNaissance;

	  private String moteDePasse;

	  private String adresse;

	  private String photo;

	  private EntrepriseDTO entreprise;

	  private List<RoleDTO> roles;
	
	  public static UtilisateurDTO fromEntity(Utilisateur utilisateur) {
		    if (utilisateur == null) {
		      return null;
		    }

		    return UtilisateurDTO.builder()
		        .id(utilisateur.getId())
		        .nom(utilisateur.getNom())
		        .prenom(utilisateur.getPrenom())
		        .email(utilisateur.getEmail())
		        .moteDePasse(utilisateur.getMoteDePasse())
		        //.dateDeNaissance(utilisateur.get)
		        .adresse(utilisateur.getAdresse())
		        .photo(utilisateur.getPhoto())
		        .entreprise(EntrepriseDTO.fromEntity(utilisateur.getEntreprise()))
		        .roles(
		            utilisateur.getRole() != null ?
		                utilisateur.getRole().stream()
		                    .map(RoleDTO::fromEntity)
		                    .collect(Collectors.toList()) : null
		        )
		        .build();
		  }

		  public static Utilisateur toEntity(UtilisateurDTO utilisateurDTO) {
		    if (utilisateurDTO == null) {
		      return null;
		    }

		    Utilisateur utilisateur = new Utilisateur();
		    utilisateur.setId(utilisateurDTO.getId());
		    utilisateur.setNom(utilisateurDTO.getNom());
		    utilisateur.setPrenom(utilisateurDTO.getPrenom());
		    utilisateur.setEmail(utilisateurDTO.getEmail());
		    utilisateur.setMoteDePasse(utilisateurDTO.getMoteDePasse());
		    //utilisateur.setDateDeNaissance(utilisateurDTO.getDateDeNaissance());
		    utilisateur.setAdresse(utilisateurDTO.getAdresse());
		    utilisateur.setPhoto(utilisateurDTO.getPhoto());
		    utilisateur.setEntreprise(EntrepriseDTO.toEntity(utilisateurDTO.getEntreprise()));

		    return utilisateur;
		  }
	  
}
