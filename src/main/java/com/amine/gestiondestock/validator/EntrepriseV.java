package com.amine.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import com.amine.gestiondestock.DTO.EntrepriseDTO;
import org.springframework.util.StringUtils;


public class EntrepriseV {

	  public static List<String> validate(EntrepriseDTO entrepriseDTO) {
		    List<String> errors = new ArrayList<>();
		    if (entrepriseDTO == null) {
		      errors.add("Veuillez renseigner le nom de l'entreprise");
		      errors.add("Veuillez reseigner la description de l'entreprise");
		      errors.add("Veuillez reseigner l'email de l'entreprise");
		      errors.add("Veuillez reseigner le numero de telephone de l'entreprise");
		      errors.add("Veuillez reseigner l'adresse de l'entreprise");

		      return errors;
		    }

		    if (!StringUtils.hasLength(entrepriseDTO.getNom())) {
		      errors.add("Veuillez renseigner le nom de l'entreprise");
		    }
		    if (!StringUtils.hasLength(entrepriseDTO.getDescription())) {
		      errors.add("Veuillez reseigner la description de l'entreprise");
		    }
		    if (!StringUtils.hasLength(entrepriseDTO.getEmail())) {
		      errors.add("Veuillez reseigner l'email de l'entreprise");
		    }
		    if (!StringUtils.hasLength(entrepriseDTO.getNumtelephone())) {
		      errors.add("Veuillez reseigner le numero de telephone de l'entreprise");
		    }

		    if (!StringUtils.hasLength(entrepriseDTO.getAdresse())) {
			  errors.add("Veuillez reseigner l'adresse de l'entreprise");
			}
		    return errors;
		  }
	
}
