package com.amine.gestiondestock.services.servicImplimentation;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import com.amine.gestiondestock.DTO.*;
import com.amine.gestiondestock.Exception.EntityNotFoundException;
import com.amine.gestiondestock.Exception.ErrorCodes;
import com.amine.gestiondestock.Exception.InvalidEntityException;
import com.amine.gestiondestock.Exception.InvalidOperationException;
import com.amine.gestiondestock.model.*;
import com.amine.gestiondestock.repos.ArticleRepos;
import com.amine.gestiondestock.repos.ClientRepos;
import com.amine.gestiondestock.repos.CommandeClientRepos;
import com.amine.gestiondestock.repos.LigneCommandeClientRepos;
import com.amine.gestiondestock.services.CommandeClientServ;
import com.amine.gestiondestock.services.MouvementStockServ;
import com.amine.gestiondestock.validator.ArticleV;
import com.amine.gestiondestock.validator.CommandClientV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;




import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CommandeClientServImpl implements CommandeClientServ {
	
	private CommandeClientRepos commandeClientRep;
	private LigneCommandeClientRepos ligneCommandeClientRep;
	private ClientRepos clientRep;
	private ArticleRepos articleRep;
	private MouvementStockServ mvtStkService;

	  @Autowired
	  public CommandeClientServImpl(CommandeClientRepos commandeClientRepos,
	      ClientRepos clientRepos, ArticleRepos articleRepos, LigneCommandeClientRepos ligneCommandeClientRepos,
	      MouvementStockServ mvtStkService) {
		  this.commandeClientRep = commandeClientRepos;
		  this.ligneCommandeClientRep = ligneCommandeClientRepos;
		  this.clientRep = clientRepos;
		  this.articleRep = articleRepos;
		  this.mvtStkService = mvtStkService;
	  }
	
	  
	  @Override
	  public CommandeClientDTO save(CommandeClientDTO dto) {

	    List<String> errors = CommandClientV.validate(dto);


	    if (dto.getId() != null && dto.isCommandeLivree()) {
	      throw new EntityNotFoundException("Impossible de modifier la commande lorsqu'elle est livree");
	    }

	    Optional<Client> client = clientRep.findById(dto.getClient().getId());
	    if (client.isEmpty()) {
	      log.warn("Client with ID {} was not found in the DB", dto.getClient().getId());
	      throw new EntityNotFoundException("Aucun client avec l'ID" + dto.getClient().getId() + " n'a ete trouve dans la BDD"
	         );
	    }

	    List<String> articleErrors = new ArrayList<>();

	    if (dto.getLigneCommandeClients() != null) {
	      dto.getLigneCommandeClients().forEach(ligCmdClt -> {
	        if (ligCmdClt.getArticle() != null) {
	          Optional<Article> article = articleRep.findById(ligCmdClt.getArticle().getId());
	          if (article.isEmpty()) {
	            articleErrors.add("L'article avec l'ID " + ligCmdClt.getArticle().getId() + " n'existe pas");
	          }
	        } else {
	          articleErrors.add("Impossible d'enregister une commande avec un aticle NULL");
	        }
	      });
	    }

	    if (!articleErrors.isEmpty()) {
	      log.warn("");
	      throw new EntityNotFoundException("Article n'existe pas dans la BDD");
	    }
	    dto.setDateCommande(Instant.now());
	    CommandeClient savedCmdClt = commandeClientRep.save(CommandeClientDTO.toEntity(dto));

	    if (dto.getLigneCommandeClients() != null) {
	      dto.getLigneCommandeClients().forEach(ligCmdClt -> {
	        LigneCommandeClient ligneCommandeClient = LigneCommandeClientDTO.toEntity(ligCmdClt);
	        ligneCommandeClient.setCommandeClient(savedCmdClt);
	        ligneCommandeClient.setIdEntreprise(dto.getIdEntreprise());
	        LigneCommandeClient savedLigneCmd = ligneCommandeClientRep.save(ligneCommandeClient);
	      });
	    }

	    return CommandeClientDTO.fromEntity(savedCmdClt);
	  }
	  
	  
	  @Override
	  public CommandeClientDTO findById(Integer id) {
	    if (id == null) {
	      log.error("Commande client ID is NULL");
	      return null;
	    }
	    return commandeClientRep.findById(id)
	        .map(CommandeClientDTO::fromEntity)
	        .orElseThrow(() -> new EntityNotFoundException(
	            "Aucune commande client n'a ete trouve avec l'ID " + id
	        ));
	  }

	  @Override
	  public CommandeClientDTO findByCode(String code) {
	    if (!StringUtils.hasLength(code)) {
	      log.error("Commande client CODE is NULL");
	      return null;
	    }
	    return commandeClientRep.findCommandeClientByCodeCommande(code)
	        .map(CommandeClientDTO::fromEntity)
	        .orElseThrow(() -> new EntityNotFoundException(
	            "Aucune commande client n'a ete trouve avec le CODE " + code
	        ));
	  }
	  
	  
	  
	  @Override
	  public CommandeClientDTO updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
	    checkIdCommande(idCommande);
	    checkIdLigneCommande(idLigneCommande);

	    if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0) {
	      log.error("L'ID de la ligne commande is NULL");
	      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une quantite null ou ZERO",
	          ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
	    }

	    CommandeClientDTO commandeClient = checkEtatCommande(idCommande);
	    Optional<LigneCommandeClient> ligneCommandeClientOptional = findLigneCommandeClient(idLigneCommande);

	    LigneCommandeClient ligneCommandeClient = ligneCommandeClientOptional.get();
	    ligneCommandeClient.setQuantite(quantite);
	    ligneCommandeClientRep.save(ligneCommandeClient);

	    return commandeClient;
	  }
	  
	  private void checkIdArticle(Integer idArticle, String msg) {
		    if (idArticle == null) {
		      log.error("L'ID de " + msg + " is NULL");
		      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un " + msg + " ID article null",
		          ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
		    }
		  }
	  
	  @Override
	  public CommandeClientDTO updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
	    checkIdCommande(idCommande);
	    checkIdLigneCommande(idLigneCommande);
	    checkIdArticle(idArticle, "nouvel");

	    CommandeClientDTO commandeClient = checkEtatCommande(idCommande);

	    Optional<LigneCommandeClient> ligneCommandeClient = findLigneCommandeClient(idLigneCommande);

	    Optional<Article> articleOptional = articleRep.findById(idArticle);
	    if (articleOptional.isEmpty()) {
	      throw new EntityNotFoundException(
	          "Aucune article n'a ete trouve avec l'ID " + idArticle, ErrorCodes.ARTICLE_NOT_FOUND);
	    }

	    List<String> errors = ArticleV.validate(ArticleDTO.fromEntity(articleOptional.get()));
	    if (!errors.isEmpty()) {
	      throw new InvalidEntityException("Article invalid", ErrorCodes.ARTICLE_NOT_VALID, errors);
	    }

	    LigneCommandeClient ligneCommandeClientToSaved = ligneCommandeClient.get();
	    ligneCommandeClientToSaved.setArticle(articleOptional.get());
	    ligneCommandeClientRep.save(ligneCommandeClientToSaved);

	    return commandeClient;
	  }

	  @Override
	  public CommandeClientDTO updateClient(Integer idCommande, Integer idClient) {
	    checkIdCommande(idCommande);
	    if (idClient == null) {
	      log.error("L'ID du client is NULL");
	      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID client null",
	          ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
	    }
	    CommandeClientDTO commandeClient = checkEtatCommande(idCommande);
	    Optional<Client> clientOptional = clientRep.findById(idClient);
	    if (clientOptional.isEmpty()) {
	      throw new EntityNotFoundException(
	          "Aucun client n'a ete trouve avec l'ID " + idClient, ErrorCodes.CLIENT_NOT_FOUND);
	    }
	    commandeClient.setClient(ClientDTO.fromEntity(clientOptional.get()));

	    return CommandeClientDTO.fromEntity(
	        commandeClientRep.save(CommandeClientDTO.toEntity(commandeClient))
	    );
	  }
	  
	  
	  

	  @Override
	  public List<CommandeClientDTO> findAll() {
	    return commandeClientRep.findAll().stream()
	        .map(CommandeClientDTO::fromEntity)
	        .collect(Collectors.toList());
	  }
	  @Override
	  public void delete(Integer id) {
	    if (id == null) {
	      log.error("Commande client ID is NULL");
	      return;
	    }
	    List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRep.findAllByCommandeClientId(id);
	    commandeClientRep.deleteById(id);
	  }
	  
	  
	  @Override
	  public List<LigneCommandeClientDTO> findAllLignesCommandesClientByCommandeClientId(Integer idCommande) {
	    return ligneCommandeClientRep.findAllByCommandeClientId(idCommande).stream()
	        .map(LigneCommandeClientDTO::fromEntity)
	        .collect(Collectors.toList());
	  }

	  
	  
	  private void checkIdCommande(Integer idCommande) {
		    if (idCommande == null) {
		      log.error("Commande client ID is NULL");
		      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID null",
		          ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
		    }
		  }
	  private CommandeClientDTO checkEtatCommande(Integer idCommande) {
		    CommandeClientDTO commandeClient = findById(idCommande);
		    if (commandeClient.isCommandeLivree()) {
		      throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
		    }
		    return commandeClient;
		  }
	  
	  private void updateMvtStk(Integer idCommande) {
		    List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRep.findAllByCommandeClientId(idCommande);
		    ligneCommandeClients.forEach(lig -> {
		      effectuerSortie(lig);
		    });
		  }
	  
	  @Override
	  public CommandeClientDTO updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
	    checkIdCommande(idCommande);
	    if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
	      log.error("L'etat de la commande client is NULL");
	      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un etat null"
	        );
	    }
	    CommandeClientDTO commandeClient = checkEtatCommande(idCommande);
	    commandeClient.setEtatCommande(etatCommande);

	    CommandeClient savedCmdClt = commandeClientRep.save(CommandeClientDTO.toEntity(commandeClient));
	    if (commandeClient.isCommandeLivree()) {
	      updateMvtStk(idCommande);
	    }

	    return CommandeClientDTO.fromEntity(savedCmdClt);
	  }
	  
	  
	  private void effectuerSortie(LigneCommandeClient lig) {
		  MouvementDeStockDTO mvtStkDto = MouvementDeStockDTO.builder()
		        .article(ArticleDTO.fromEntity(lig.getArticle()))
		        .date(Instant.now())
		        .type(TypeMouvement.SORTIE)
		        .sourceMouvement(SourceMouvement.COMMANDE_CLIENT)
		        .quantite(lig.getQuantite())
		        .idEntreprise(lig.getIdEntreprise())
		        .build();
		    mvtStkService.sortieStock(mvtStkDto);
		  }


	  private void checkIdLigneCommande(Integer idLigneCommande) {
	    if (idLigneCommande == null) {
	      log.error("L'ID de la ligne commande is NULL");
	      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une ligne de commande null",
	          ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
	    }
	  }


	  private Optional<LigneCommandeClient> findLigneCommandeClient(Integer idLigneCommande) {
	    Optional<LigneCommandeClient> ligneCommandeClientOptional = ligneCommandeClientRep.findById(idLigneCommande);
	    if (ligneCommandeClientOptional.isEmpty()) {
	      throw new EntityNotFoundException(
	          "Aucune ligne commande client n'a ete trouve avec l'ID " + idLigneCommande, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
	    }
	    return ligneCommandeClientOptional;
	  }
	  
	  @Override
	  public CommandeClientDTO deleteArticle(Integer idCommande, Integer idLigneCommande) {
	    checkIdCommande(idCommande);
	    checkIdLigneCommande(idLigneCommande);

	    CommandeClientDTO commandeClient = checkEtatCommande(idCommande);
	    // Just to check the LigneCommandeClient and inform the client in case it is absent
	    findLigneCommandeClient(idLigneCommande);
	    ligneCommandeClientRep.deleteById(idLigneCommande);

	    return commandeClient;
	  }
	  
	  
}
