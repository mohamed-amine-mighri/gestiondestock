package com.amine.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import com.amine.gestiondestock.DTO.ClientDTO;
import org.springframework.util.StringUtils;


public class ClientV {

	  public static List<String> validate(ClientDTO clientDTO) {
		    List<String> errors = new ArrayList<>();

		    if (clientDTO == null) {
		      errors.add("Veuillez renseigner le nom du client");
		      errors.add("Veuillez renseigner le prenom du client");
		      errors.add("Veuillez renseigner le Mail du client");
		      errors.add("Veuillez renseigner le numero de telephone du client");
		      errors.add("Veuillez renseigner l'adresse du client");
		      return errors;
		    }

		    if (!StringUtils.hasLength(clientDTO.getNom())) {
		      errors.add("Veuillez renseigner le nom du client");
		    }
		    if (!StringUtils.hasLength(clientDTO.getPrenom())) {
		      errors.add("Veuillez renseigner le prenom du client");
		    }
		    if (!StringUtils.hasLength(clientDTO.getEmail())) {
		      errors.add("Veuillez renseigner le Mail du client");
		    }
		    if (!StringUtils.hasLength(clientDTO.getNumtelephone())) {
		      errors.add("Veuillez renseigner le numero de telephone du client");
		    }
		    if (!StringUtils.hasLength(clientDTO.getAdresse())) {
			  errors.add("Veuillez renseigner l'adresse du client");
			}
		    return errors;
		  } 
	
}
