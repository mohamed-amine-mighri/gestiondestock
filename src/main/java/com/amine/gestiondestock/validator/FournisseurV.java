package com.amine.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import com.amine.gestiondestock.DTO.FournisseurDTO;
import org.springframework.util.StringUtils;


public class FournisseurV {

	  public static List<String> validate(FournisseurDTO fournisseurDTO) {
		    List<String> errors = new ArrayList<>();

		    if (fournisseurDTO == null) {
		      errors.add("Veuillez renseigner le nom du fournisseur");
		      errors.add("Veuillez renseigner le prenom du fournisseur");
		      errors.add("Veuillez renseigner le Mail du fournisseur");
		      errors.add("Veuillez renseigner le numero de telephone du fournisseur");
		      errors.add("Veuillez renseigner l'adresse de fournisseur");
		      return errors;
		    }

		    if (!StringUtils.hasLength(fournisseurDTO.getNom())) {
		      errors.add("Veuillez renseigner le nom du fournisseur");
		    }
		    if (!StringUtils.hasLength(fournisseurDTO.getPrenom())) {
		      errors.add("Veuillez renseigner le prenom du fournisseur");
		    }
		    if (!StringUtils.hasLength(fournisseurDTO.getEmail())) {
		      errors.add("Veuillez renseigner le Mail du fournisseur");
		    }
		    if (!StringUtils.hasLength(fournisseurDTO.getNumtelephone())) {
		      errors.add("Veuillez renseigner le numero de telephone du fournisseur");
		    }
		    if (!StringUtils.hasLength(fournisseurDTO.getAdresse())) {
			  errors.add("Veuillez renseigner l'adresse de fournisseur");
			}
		   return errors;
		  }

	
}
