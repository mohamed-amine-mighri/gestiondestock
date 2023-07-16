package com.amine.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import com.amine.gestiondestock.DTO.CategorieDTO;
import org.springframework.util.StringUtils;

public class CategorieV {

	public static List<String> validate(CategorieDTO categoryDTO) {
	    List<String> errors = new ArrayList<>();

	    if (categoryDTO == null || !StringUtils.hasLength(categoryDTO.getCodeCategorie())) {
	      errors.add("Veuillez renseigner le code de la categorie");
	    }
	    return errors;
	  }
	
}
