package com.amine.gestiondestock.DTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.amine.gestiondestock.DTO.ArticleDTO;
import com.amine.gestiondestock.model.CommandeFournisseur;
import com.amine.gestiondestock.model.EtatCommande;
import com.amine.gestiondestock.model.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LigneCommandeFournisseurDTO {

	private Integer id;

	private ArticleDTO article;

	private CommandeFournisseur commandeFournisseur;

	private BigDecimal quantite;

	private BigDecimal prixUnitaire;

	private Integer idEntreprise;

	public static LigneCommandeFournisseurDTO fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
		if (ligneCommandeFournisseur == null) {
			return null;
		}
		return LigneCommandeFournisseurDTO.builder()
				.id(ligneCommandeFournisseur.getId())
				.article(ArticleDTO.fromEntity(ligneCommandeFournisseur.getArticle()))
				.quantite(ligneCommandeFournisseur.getQuantite())
				.prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
				.idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
				.build();
	}

	public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDTO dto) {
		if (dto == null) {
			return null;
		}

		LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
		ligneCommandeFournisseur.setId(dto.getId());
		ligneCommandeFournisseur.setArticle(ArticleDTO.toEntity(dto.getArticle()));
		ligneCommandeFournisseur.setPrixUnitaire(dto.getPrixUnitaire());
		ligneCommandeFournisseur.setQuantite(dto.getQuantite());
		ligneCommandeFournisseur.setIdEntreprise(dto.getIdEntreprise());
		return ligneCommandeFournisseur;
	}
		  
}
