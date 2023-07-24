package com.amine.gestiondestock.services;

import java.math.BigDecimal;
import java.util.List;


import com.amine.gestiondestock.DTO.CommandeClientDTO;
import com.amine.gestiondestock.DTO.LigneCommandeClientDTO;
import com.amine.gestiondestock.model.EtatCommande;

public interface CommandeClientServ {

	CommandeClientDTO findById(Integer id);
	
	List<CommandeClientDTO> findAll();
	
	CommandeClientDTO updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

	CommandeClientDTO updateClient(Integer idCommande, Integer idClient);
	
	//CommandeClientDTO updateClient(Integer idCommande, Integer idClient);
	
	//CommandeClientDTO updateArticle(Integer idCommande, Integer idLigneCommande, Integer newIdArticle);
	
	CommandeClientDTO updateEtatCommande(Integer idCommande, EtatCommande etatCommande);
	
	CommandeClientDTO save(CommandeClientDTO dto);
		
	CommandeClientDTO deleteArticle(Integer idCommande, Integer idLigneCommande);

	CommandeClientDTO updateArticle(Integer idCommande, Integer idLigneCommande, Integer newIdArticle);
	
	CommandeClientDTO findByCode(String code);

	List<LigneCommandeClientDTO> findAllLignesCommandesClientByCommandeClientId(Integer idCommande);

	void delete(Integer Id);
	
}
