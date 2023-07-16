package com.amine.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import com.amine.gestiondestock.DTO.UtilisateurDTO;
import org.springframework.util.StringUtils;


public class UtilisateurV {

	  public static List<String> validate(UtilisateurDTO utilisateurDTO) {
		    List<String> errors = new ArrayList<>();

		    if (utilisateurDTO == null) {
		      errors.add("Veuillez renseigner le nom d'utilisateur");
		      errors.add("Veuillez renseigner le prenom d'utilisateur");
		      errors.add("Veuillez renseigner le mot de passe d'utilisateur");
		      errors.add("Veuillez renseigner l'adresse d'utilisateur");
		      //errors.add("Veuillez renseigner l'adresse d'utilisateur");
		      errors.add("Veuillez renseigner l'adresse email d'utilisateur");

		      return errors;
		    }

		    if (!StringUtils.hasLength(utilisateurDTO.getNom())) {
		      errors.add("Veuillez renseigner le nom d'utilisateur");
		    }
		    if (!StringUtils.hasLength(utilisateurDTO.getPrenom())) {
		      errors.add("Veuillez renseigner le prenom d'utilisateur");
		    }
		    if (!StringUtils.hasLength(utilisateurDTO.getEmail())) {
		      errors.add("Veuillez renseigner l'email d'utilisateur");
		    }
		    if (!StringUtils.hasLength(utilisateurDTO.getMoteDePasse())) {
		      errors.add("Veuillez renseigner le mot de passe d'utilisateur");
		    }
		    if (!StringUtils.hasLength(utilisateurDTO.getAdresse())) {
			  errors.add("Veuillez renseigner l'adresse d'utilisateur");
		   }
		/*
		 * if (!StringUtils.hasLength(utilisateurDTO.getEmail())) {
		 * errors.add("Veuillez renseigner l'adresse emal d'utilisateur"); }
		 */
		    return errors;
		  }
	
}
