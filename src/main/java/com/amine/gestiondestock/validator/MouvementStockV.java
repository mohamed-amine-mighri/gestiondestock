package com.amine.gestiondestock.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.amine.gestiondestock.DTO.MouvementDeStockDTO;
import org.springframework.util.StringUtils;


public class MouvementStockV {

	  public static List<String> validate(MouvementDeStockDTO mouvementDeStockDTO) {
		    List<String> errors = new ArrayList<>();
		    if (mouvementDeStockDTO == null) {
		      errors.add("Veuillez renseigner la date du mouvenent");
		      errors.add("Veuillez renseigner la quantite du mouvenent");
		      errors.add("Veuillez renseigner l'article");
		      errors.add("Veuillez renseigner le type du mouvement");
		      
		      return errors;
		    }
		    if (mouvementDeStockDTO.getDate() == null) {
		      errors.add("Veuillez renseigner la date du mouvenent");
		    }
		    if (mouvementDeStockDTO.getQuantite() == null || mouvementDeStockDTO.getQuantite().compareTo(BigDecimal.ZERO) == 0) {
		      errors.add("Veuillez renseigner la quantite du mouvenent");
		    }
		    if (mouvementDeStockDTO.getArticle() == null || mouvementDeStockDTO.getArticle().getId() == null) {
		      errors.add("Veuillez renseigner l'article");
		    }
		    if (!StringUtils.hasLength(mouvementDeStockDTO.getType().name())) {
		      errors.add("Veuillez renseigner le type du mouvement");
		    }
		    return errors;
		  }
	
}
