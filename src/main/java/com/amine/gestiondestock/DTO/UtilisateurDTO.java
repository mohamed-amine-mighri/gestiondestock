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

	private Instant dateDeNaissance;

	private String moteDePasse;

	private AdresseDto adresse;

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
				.dateDeNaissance(utilisateur.getDateDeNaissance())
				.adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
				.photo(utilisateur.getPhoto())
				.entreprise(EntrepriseDTO.fromEntity(utilisateur.getEntreprise()))
				.roles(
						utilisateur.getRoles() != null ?
								utilisateur.getRoles().stream()
										.map(RoleDTO::fromEntity)
										.collect(Collectors.toList()) : null
				)
				.build();
	}

	public static Utilisateur toEntity(UtilisateurDTO dto) {
		if (dto == null) {
			return null;
		}

		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(dto.getId());
		utilisateur.setNom(dto.getNom());
		utilisateur.setPrenom(dto.getPrenom());
		utilisateur.setEmail(dto.getEmail());
		utilisateur.setMoteDePasse(dto.getMoteDePasse());
		utilisateur.setDateDeNaissance(dto.getDateDeNaissance());
		utilisateur.setAdresse(AdresseDto.toEntity(dto.getAdresse()));
		utilisateur.setPhoto(dto.getPhoto());
		utilisateur.setEntreprise(EntrepriseDTO.toEntity(dto.getEntreprise()));

		return utilisateur;
	}
}
