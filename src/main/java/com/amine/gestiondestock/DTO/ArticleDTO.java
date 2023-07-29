package com.amine.gestiondestock.DTO;

import java.math.BigDecimal;

import com.amine.gestiondestock.model.Article;
import com.amine.gestiondestock.model.Entreprise;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ArticleDTO {

	private Integer id;

	private String codeArticle;

	private String designation;

	private BigDecimal prixUnitaireHt;

	private BigDecimal tauxTva;

	private BigDecimal prixUnitaireTtc;

	private String photo;

	private CategorieDTO category;

	private Integer idEntreprise;

	public static ArticleDTO fromEntity(Article article) {
		if (article == null) {
			return null;
		}
		return ArticleDTO.builder()
				.id(article.getId())
				.codeArticle(article.getCodeArticle())
				.designation(article.getDesignation())
				.photo(article.getPhoto())
				.prixUnitaireHt(article.getPrixUnitaireHt())
				.prixUnitaireTtc(article.getPrixUnitaireTtc())
				.tauxTva(article.getTauxTva())
				.idEntreprise(article.getIdEntreprise())
				.category(CategorieDTO.fromEntity(article.getCategory()))
				.build();
	}

	public static Article toEntity(ArticleDTO articleDto) {
		if (articleDto == null) {
			return null;
		}
		Article article = new Article();
		article.setId(articleDto.getId());
		article.setCodeArticle(articleDto.getCodeArticle());
		article.setDesignation(articleDto.getDesignation());
		article.setPhoto(articleDto.getPhoto());
		article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
		article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
		article.setTauxTva(articleDto.getTauxTva());
		article.setIdEntreprise(articleDto.getIdEntreprise());
		article.setCategory(CategorieDTO.toEntity(articleDto.getCategory()));
		return article;
	}
}
