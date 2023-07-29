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
				.code(commandeClient.getCode())
				.dateCommande(commandeClient.getDateCommande())
				.etatCommande(commandeClient.getEtatCommande())
				.client(ClientDTO.fromEntity(commandeClient.getClient()))
				.idEntreprise(commandeClient.getIdEntreprise())
				.build();

	}

	public static CommandeClient toEntity(CommandeClientDTO dto) {
		if (dto == null) {
			return null;
		}
		CommandeClient commandeClient = new CommandeClient();
		commandeClient.setId(dto.getId());
		commandeClient.setCode(dto.getCode());
		commandeClient.setClient(ClientDTO.toEntity(dto.getClient()));
		commandeClient.setDateCommande(dto.getDateCommande());
		commandeClient.setEtatCommande(dto.getEtatCommande());
		commandeClient.setIdEntreprise(dto.getIdEntreprise());
		return commandeClient;
	}

	public boolean isCommandeLivree() {
		return EtatCommande.LIVREE.equals(this.etatCommande);
	}
}
