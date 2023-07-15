package com.amine.gestiondestock.DTO;



import java.time.Instant;
import java.util.List;

import com.amine.gestiondestock.model.CommandeClient;
import com.amine.gestiondestock.model.EtatCommande;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CommandeClientDTO {

	  private Integer id;

	  private String code;

	  private Instant dateCommande;

	  private EtatCommande etatCommande;

	  private ClientDTO client;

	  private Integer idEntreprise;

	  private List<LigneCommandeClientDTO> ligneCommandeClients;

	  
	  public static CommandeClientDTO fromEntity(CommandeClient commandeClient) {
		    if (commandeClient == null) {
		      return null;
		    }
		    return CommandeClientDTO.builder()
		        .id(commandeClient.getId())
		        .code(commandeClient.getCodeCommande())
		        .dateCommande(commandeClient.getDate())
		        .etatCommande(commandeClient.getEtatCommande())
		        .client(ClientDTO.fromEntity(commandeClient.getClient()))
		        .idEntreprise(commandeClient.getIdEntreprise())
		        .build();
		  }
	 
	  // CommandeClient --> CommandeClientDTO
	  
	  public static CommandeClient toEntity(CommandeClientDTO commandeClientDTO) {
	    if (commandeClientDTO == null) {
	      return null;
	    }
	    CommandeClient commandeClient = new CommandeClient();
	    commandeClient.setId(commandeClientDTO.getId());
	    commandeClient.setCodeCommande(commandeClientDTO.getCode());
	    commandeClient.setDate(commandeClientDTO.getDateCommande());
	    commandeClient.setEtatCommande(commandeClientDTO.getEtatCommande());
	    commandeClient.setClient(ClientDTO.toEntity(commandeClientDTO.getClient()));
	    commandeClient.setIdEntreprise(commandeClientDTO.getIdEntreprise());
	    return commandeClient;
	  }
	  
	  public boolean isCommandeLivree() {

		  return EtatCommande.LIVREE.equals(this.etatCommande);

	  }
	 
	  
}
