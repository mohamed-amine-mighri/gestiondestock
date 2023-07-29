package com.amine.gestiondestock.DTO;

import java.math.BigDecimal;
import java.time.Instant;

import com.amine.gestiondestock.DTO.ArticleDTO;
import com.amine.gestiondestock.model.MvtStk;
import com.amine.gestiondestock.model.SourceMouvement;
import com.amine.gestiondestock.model.TypeMouvement;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MouvementDeStockDTO {

	  private Integer id;

	  private Instant date;

	  private BigDecimal quantite;

	  private ArticleDTO article;

	  private TypeMouvement type;
	  
	  private SourceMouvement sourceMouvement;

	  private Integer idEntreprise;
	
	  
	  public static MouvementDeStockDTO fromEntity(MvtStk mouvementDeStock) {
		    if (mouvementDeStock == null) {
		      return null;
		    }

		    return MouvementDeStockDTO.builder()
		        .id(mouvementDeStock.getId())
		        .date(mouvementDeStock.getDateMvt())
		        .quantite(mouvementDeStock.getQuantite())
		        .article(ArticleDTO.fromEntity(mouvementDeStock.getArticle()))
		        .type(mouvementDeStock.getTypeMvt())
		        .idEntreprise(mouvementDeStock.getIdEntreprise())
		        .build();
		  }

		  public static MvtStk toEntity(MouvementDeStockDTO mouvementDeStockDTO) {
		    if (mouvementDeStockDTO == null) {
		      return null;
		    }

		    MvtStk mouvementDeStock = new MvtStk();
		    mouvementDeStock.setId(mouvementDeStockDTO.getId());
		    mouvementDeStock.setDateMvt(mouvementDeStockDTO.getDate());
		    mouvementDeStock.setQuantite(mouvementDeStockDTO.getQuantite());
		    mouvementDeStock.setArticle(ArticleDTO.toEntity(mouvementDeStockDTO.getArticle()));
		    mouvementDeStock.setTypeMvt(mouvementDeStockDTO.getType());
		    mouvementDeStock.setIdEntreprise(mouvementDeStockDTO.getIdEntreprise());
		    return mouvementDeStock;
		  }
	  
}
