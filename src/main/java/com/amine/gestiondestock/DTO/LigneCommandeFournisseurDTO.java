package com.amine.gestiondestock.DTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.amine.gestiondestock.DTO.ArticleDTO;
import com.amine.gestiondestock.model.EtatCommande;
import com.amine.gestiondestock.model.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LigneCommandeFournisseurDTO {

	  private Integer id;

	  private ArticleDTO article;
	  
	  private Instant dateCommande;

	  private CommandeFournisseurDTO commandeFournisseur;
	  
	  private EtatCommande etatCommande;

	  private FournisseurDTO fournisseur;

	  private BigDecimal quantite;

	  private BigDecimal prixUnitaire;

	  private Integer idEntreprise;
	
	  private List<LigneCommandeFournisseurDTO> ligneCommandeFournisseurs;

	  
	  public static LigneCommandeFournisseurDTO fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
		    if (ligneCommandeFournisseur == null) {
		      return null;
		    }
		    return LigneCommandeFournisseurDTO.builder()
		        .id(ligneCommandeFournisseur.getId())
		        .article(ArticleDTO.fromEntity(ligneCommandeFournisseur.getArticle()))
		        .quantite(ligneCommandeFournisseur.getQuantite())
		        .prixUnitaire(ligneCommandeFournisseur.getPrix())
		        .idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
		        .build();
		  }

		  public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDTO ligneCommandeFournisseurDTO) {
		    if (ligneCommandeFournisseurDTO == null) {
		      return null;
		    }

		    LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
		    ligneCommandeFournisseur.setId(ligneCommandeFournisseurDTO.getId());
		    ligneCommandeFournisseur.setArticle(ArticleDTO.toEntity(ligneCommandeFournisseurDTO.getArticle()));
		    ligneCommandeFournisseur.setPrix(ligneCommandeFournisseurDTO.getPrixUnitaire());
		    ligneCommandeFournisseur.setQuantite(ligneCommandeFournisseurDTO.getQuantite());
		    ligneCommandeFournisseur.setIdEntreprise(ligneCommandeFournisseurDTO.getIdEntreprise());
		    return ligneCommandeFournisseur;
		  }
		  
		  
}
