package com.amine.gestiondestock.controller;

import com.amine.gestiondestock.DTO.CommandeClientDTO;
import com.amine.gestiondestock.DTO.LigneCommandeClientDTO;
import com.amine.gestiondestock.controller.API.CommandeClientApi;
import com.amine.gestiondestock.model.EtatCommande;
import com.amine.gestiondestock.services.CommandeClientService;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandeClientController implements CommandeClientApi {

  private CommandeClientService commandeClientService;

  @Autowired
  public CommandeClientController(CommandeClientService commandeClientService) {
    this.commandeClientService = commandeClientService;
  }

  @Override
  public ResponseEntity<CommandeClientDTO> save(CommandeClientDTO dto) {
    return ResponseEntity.ok(commandeClientService.save(dto));
  }

  @Override
  public ResponseEntity<CommandeClientDTO> updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
    return ResponseEntity.ok(commandeClientService.updateEtatCommande(idCommande, etatCommande));
  }

  @Override
  public ResponseEntity<CommandeClientDTO> updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
    return ResponseEntity.ok(commandeClientService.updateQuantiteCommande(idCommande, idLigneCommande, quantite));
  }

  @Override
  public ResponseEntity<CommandeClientDTO> updateClient(Integer idCommande, Integer idClient) {
    return ResponseEntity.ok(commandeClientService.updateClient(idCommande, idClient));
  }

  @Override
  public ResponseEntity<CommandeClientDTO> updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
    return ResponseEntity.ok(commandeClientService.updateArticle(idCommande, idLigneCommande, idArticle));
  }

  @Override
  public ResponseEntity<CommandeClientDTO> deleteArticle(Integer idCommande, Integer idLigneCommande) {
    return ResponseEntity.ok(commandeClientService.deleteArticle(idCommande, idLigneCommande));
  }

  @Override
  public ResponseEntity<CommandeClientDTO> findById(Integer id) {
    return ResponseEntity.ok(commandeClientService.findById(id));
  }

  @Override
  public ResponseEntity<CommandeClientDTO> findByCode(String code) {
    return ResponseEntity.ok(commandeClientService.findByCode(code));
  }

  @Override
  public ResponseEntity<List<CommandeClientDTO>> findAll() {
    return ResponseEntity.ok(commandeClientService.findAll());
  }

  @Override
  public ResponseEntity<List<LigneCommandeClientDTO>> findAllLignesCommandesClientByCommandeClientId(Integer idCommande) {
    return ResponseEntity.ok(commandeClientService.findAllLignesCommandesClientByCommandeClientId(idCommande));
  }

  @Override
  public ResponseEntity<Void> delete(Integer id) {
    commandeClientService.delete(id);
    return ResponseEntity.ok().build();
  }
}
