package com.amine.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import com.amine.gestiondestock.DTO.ArticleDTO;
import org.springframework.util.StringUtils;



public class ArticleV {

	public static List<String> validate(ArticleDTO articleDTO) {
	    List<String> errors = new ArrayList<>();

	    if (articleDTO == null) {
	      errors.add("Veuillez renseigner le code de l'article");
	      errors.add("Veuillez renseigner la designation de l'article");
	      errors.add("Veuillez renseigner le prix unitaire de l'article");
	      errors.add("Veuillez selectionner une categorie");
	      return errors;
	    }
	    
	    if (!StringUtils.hasLength(articleDTO.getCodeArticle())) {
	        errors.add("Veuillez renseigner le code de l'article");
	      }
	    if (!StringUtils.hasLength(articleDTO.getDesignation())) {
	        errors.add("Veuillez renseigner la designation de l'article");
	      }
	    if ((articleDTO.getPrixUnitaireHt())==null) {
	        errors.add("Veuillez renseigner le prix unitaire de l'article");
	      }
	    if (articleDTO.getCategory() == null || articleDTO.getCategory().getId() == null) {
	        errors.add("Veuillez selectionner une categorie");
	      }
	    return errors;
	}
	
}
