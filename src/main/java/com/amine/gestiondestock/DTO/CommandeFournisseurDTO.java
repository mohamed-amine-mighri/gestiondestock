package com.amine.gestiondestock.DTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.amine.gestiondestock.DTO.FournisseurDTO;
import com.amine.gestiondestock.DTO.LigneCommandeFournisseurDTO;
import com.amine.gestiondestock.model.CommandeFournisseur;
import com.amine.gestiondestock.model.EtatCommande;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class CommandeFournisseurDTO {

	private Integer id;

	private String code;

	private Instant dateCommande;

	private EtatCommande etatCommande;

	private FournisseurDTO fournisseur;

	private Integer idEntreprise;

	private List<LigneCommandeFournisseurDTO> ligneCommandeFournisseurs;

	public static CommandeFournisseurDTO fromEntity(CommandeFournisseur commandeFournisseur) {
		if (commandeFournisseur == null) {
			return null;
		}
		return CommandeFournisseurDTO.builder()
				.id(commandeFournisseur.getId())
				.code(commandeFournisseur.getCode())
				.dateCommande(commandeFournisseur.getDateCommande())
				.fournisseur(FournisseurDTO.fromEntity(commandeFournisseur.getFournisseur()))
				.etatCommande(commandeFournisseur.getEtatCommande())
				.idEntreprise(commandeFournisseur.getIdEntreprise())
				.build();
	}

	public static CommandeFournisseur toEntity(CommandeFournisseurDTO dto) {
		if (dto == null) {
			return null;
		}
		CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
		commandeFournisseur.setId(dto.getId());
		commandeFournisseur.setCode(dto.getCode());
		commandeFournisseur.setDateCommande(dto.getDateCommande());
		commandeFournisseur.setFournisseur(FournisseurDTO.toEntity(dto.getFournisseur()));
		commandeFournisseur.setIdEntreprise(dto.getIdEntreprise());
		commandeFournisseur.setEtatCommande(dto.getEtatCommande());
		return commandeFournisseur;
	}

	public boolean isCommandeLivree() {
		return EtatCommande.LIVREE.equals(this.etatCommande);
	}
	  
	  
}
