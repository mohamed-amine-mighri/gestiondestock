package com.amine.gestiondestock.DTO;

import java.math.BigDecimal;

import com.amine.gestiondestock.model.LigneCommandeClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LigneCommandeClientDTO {

	private Integer id;

	private ArticleDTO article;

	@JsonIgnore
	private CommandeClientDTO commandeClient;

	private BigDecimal quantite;

	private BigDecimal prixUnitaire;

	private Integer idEntreprise;

	public static LigneCommandeClientDTO fromEntity(LigneCommandeClient ligneCommandeClient) {
		if (ligneCommandeClient == null) {
			return null;
		}
		return LigneCommandeClientDTO.builder()
				.id(ligneCommandeClient.getId())
				.article(ArticleDTO.fromEntity(ligneCommandeClient.getArticle()))
				.quantite(ligneCommandeClient.getQuantite())
				.prixUnitaire(ligneCommandeClient.getPrixUnitaire())
				.idEntreprise(ligneCommandeClient.getIdEntreprise())
				.build();
	}

	public static LigneCommandeClient toEntity(LigneCommandeClientDTO dto) {
		if (dto == null) {
			return null;
		}

		LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
		ligneCommandeClient.setId(dto.getId());
		ligneCommandeClient.setArticle(ArticleDTO.toEntity(dto.getArticle()));
		ligneCommandeClient.setPrixUnitaire(dto.getPrixUnitaire());
		ligneCommandeClient.setQuantite(dto.getQuantite());
		ligneCommandeClient.setIdEntreprise(dto.getIdEntreprise());
		return ligneCommandeClient;
	}

}
