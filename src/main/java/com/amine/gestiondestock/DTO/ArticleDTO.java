package com.amine.gestiondestock.DTO;

import java.math.BigDecimal;

import com.amine.gestiondestock.model.Article;
import com.amine.gestiondestock.model.Entreprise;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
@EntityScan(basePackages="com.GTE.Gestion_Stock.model") 
public class ArticleDTO {

	
	  private Integer id;

	  private String codeArticle;

	  private String nom;

	  private BigDecimal prix;

	  private String photo;

	  private CategorieDTO category;

	  private Entreprise idEntreprise;
	
	  // Article ---> ArticleDTO
	  
	  public static ArticleDTO fromEntity(Article article) {
		    if (article == null) {
		      return null;
		    }
		    return ArticleDTO.builder()
					.id(article.getId())
					.codeArticle(article.getCodeArticle())
					.nom(article.getNom())
					.photo(article.getPhoto())
					.prix(article.getPrix())
					.idEntreprise(article.getEntreprise())
					.category(CategorieDTO.fromEntity(article.getCategory()))
					.build();
	  	}

	  // ArticleDTO --> Article
	  
		  public static Article toEntity(ArticleDTO articleDto) {
		    if (articleDto == null) {
		      return null;
		    }
		    Article article = new Article();
		    article.setId(articleDto.getId());
		    article.setCodeArticle(articleDto.getCodeArticle());
		    article.setNom(articleDto.getNom());
		    article.setPhoto(articleDto.getPhoto());
		    article.setPrix(articleDto.getPrix());
		    article.setEntreprise(articleDto.getIdEntreprise());
		    article.setCategory(CategorieDTO.toEntity(articleDto.getCategory()));
		    return article;
		  }
	
}
