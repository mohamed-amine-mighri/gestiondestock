package com.amine.gestiondestock.DTO;

import com.amine.gestiondestock.model.LigneVente;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LigneVenteDTO {

  private Integer id;

  private VentesDTO vente;

  private ArticleDTO article;

  private BigDecimal quantite;

  private BigDecimal prixUnitaire;

  private Integer idEntreprise;

  public static com.amine.gestiondestock.DTO.LigneVenteDTO fromEntity(LigneVente ligneVente) {
    if (ligneVente == null) {
      return null;
    }

    return com.amine.gestiondestock.DTO.LigneVenteDTO.builder()
        .id(ligneVente.getId())
        .vente(VentesDTO.fromEntity(ligneVente.getVente()))
        .article(ArticleDTO.fromEntity(ligneVente.getArticle()))
        .quantite(ligneVente.getQuantite())
        .prixUnitaire(ligneVente.getPrixUnitaire())
        .idEntreprise(ligneVente.getIdEntreprise())
        .build();
  }

  public static LigneVente toEntity(com.amine.gestiondestock.DTO.LigneVenteDTO dto) {
    if (dto == null) {
      return null;
    }
    LigneVente ligneVente = new LigneVente();
    ligneVente.setId(dto.getId());
    ligneVente.setVente(VentesDTO.toEntity(dto.getVente()));
    ligneVente.setArticle(ArticleDTO.toEntity(dto.getArticle()));
    ligneVente.setQuantite(dto.getQuantite());
    ligneVente.setPrixUnitaire(dto.getPrixUnitaire());
    ligneVente.setIdEntreprise(dto.getIdEntreprise());
    return ligneVente;
  }

}
