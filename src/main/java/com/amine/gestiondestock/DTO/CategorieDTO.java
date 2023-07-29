package com.amine.gestiondestock.DTO;


import java.math.BigDecimal;

import com.amine.gestiondestock.model.Category;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class CategorieDTO {

	private Integer id;

	private String codeCategorie;

	private String nom;

	private Integer idEntreprise;
	
	
	 public static CategorieDTO fromEntity(Category categorie) {
		    if (categorie == null) {
		      return null;
		    }
		    return CategorieDTO.builder()
		        .id(categorie.getId())
		        .codeCategorie(categorie.getCode())
		        .nom(categorie.getDesignation())
		        .idEntreprise(categorie.getIdEntreprise())
		        .build();
		  }
	 
	  // Article --> ArticleDTO
	  
	  public static Category toEntity(CategorieDTO categorieDto) {
	    if (categorieDto == null) {
	      return null;
	    }
	    Category categorie = new Category();
	    categorie.setId(categorieDto.getId());
	    categorie.setCode(categorieDto.getCodeCategorie());
	    categorie.setDesignation(categorieDto.getNom());
	    categorie.setIdEntreprise(categorieDto.getIdEntreprise());
	    
	    return categorie;
	  }
	
}
