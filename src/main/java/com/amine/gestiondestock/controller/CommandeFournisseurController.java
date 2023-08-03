package com.amine.gestiondestock.controller;

import com.amine.gestiondestock.DTO.CommandeFournisseurDTO;
import com.amine.gestiondestock.DTO.LigneCommandeFournisseurDTO;
import com.amine.gestiondestock.controller.API.CommandeFournisseurApi;
import com.amine.gestiondestock.model.EtatCommande;
import com.amine.gestiondestock.services.CommandeFournisseurService;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

  private CommandeFournisseurService commandeFournisseurService;

  @Autowired
  public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
    this.commandeFournisseurService = commandeFournisseurService;
  }

  @Override
  public CommandeFournisseurDTO save(CommandeFournisseurDTO dto) {
    return commandeFournisseurService.save(dto);
  }

  @Override
  public CommandeFournisseurDTO updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
    return commandeFournisseurService.updateEtatCommande(idCommande, etatCommande);
  }

  @Override
  public CommandeFournisseurDTO updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
    return commandeFournisseurService.updateQuantiteCommande(idCommande, idLigneCommande, quantite);
  }

  @Override
  public CommandeFournisseurDTO updateFournisseur(Integer idCommande, Integer idFournisseur) {
    return commandeFournisseurService.updateFournisseur(idCommande, idFournisseur);
  }

  @Override
  public CommandeFournisseurDTO updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
    return commandeFournisseurService.updateArticle(idCommande, idLigneCommande, idArticle);
  }

  @Override
  public CommandeFournisseurDTO deleteArticle(Integer idCommande, Integer idLigneCommande) {
    return commandeFournisseurService.deleteArticle(idCommande, idLigneCommande);
  }

  @Override
  public CommandeFournisseurDTO findById(Integer id) {
    return commandeFournisseurService.findById(id);
  }

  @Override
  public CommandeFournisseurDTO findByCode(String code) {
    return commandeFournisseurService.findByCode(code);
  }

  @Override
  public List<CommandeFournisseurDTO> findAll() {
    return commandeFournisseurService.findAll();
  }

  @Override
  public List<LigneCommandeFournisseurDTO> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande) {
    return commandeFournisseurService.findAllLignesCommandesFournisseurByCommandeFournisseurId(idCommande);
  }

  @Override
  public void delete(Integer id) {
    commandeFournisseurService.delete(id);
  }
}
