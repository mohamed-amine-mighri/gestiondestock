package com.amine.gestiondestock.controller.API;

import static com.amine.gestiondestock.utils.Constants.COMMANDECLIENT_ENDPOINT;

import com.amine.gestiondestock.DTO.CommandeClientDTO;
import com.amine.gestiondestock.DTO.LigneCommandeClientDTO;
import com.amine.gestiondestock.model.EtatCommande;
import io.swagger.annotations.Api;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api("commandesclients")
public interface CommandeClientApi {


  @PostMapping(COMMANDECLIENT_ENDPOINT + "/commandesclients/create")
  ResponseEntity<CommandeClientDTO> save(@RequestBody CommandeClientDTO dto);

  @PatchMapping(COMMANDECLIENT_ENDPOINT + "/commandesclients/update/etat/{idCommande}/{etatCommande}")
  ResponseEntity<CommandeClientDTO> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

  @PatchMapping(COMMANDECLIENT_ENDPOINT + "/commandesclients/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
  ResponseEntity<CommandeClientDTO> updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande,
      @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);

  @PatchMapping(COMMANDECLIENT_ENDPOINT + "/commandesclients/update/client/{idCommande}/{idClient}")
  ResponseEntity<CommandeClientDTO> updateClient(@PathVariable("idCommande") Integer idCommande, @PathVariable("idClient") Integer idClient);

  @PatchMapping(COMMANDECLIENT_ENDPOINT + "/commandesclients/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
  ResponseEntity<CommandeClientDTO> updateArticle(@PathVariable("idCommande") Integer idCommande,
      @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);

  @DeleteMapping(COMMANDECLIENT_ENDPOINT + "/commandesclients/delete/article/{idCommande}/{idLigneCommande}")
  ResponseEntity<CommandeClientDTO> deleteArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);

  @GetMapping(COMMANDECLIENT_ENDPOINT + "/commandesclients/{idCommandeClient}")
  ResponseEntity<CommandeClientDTO> findById(@PathVariable Integer idCommandeClient);

  @GetMapping(COMMANDECLIENT_ENDPOINT + "/commandesclients/filter/{codeCommandeClient}")
  ResponseEntity<CommandeClientDTO> findByCode(@PathVariable("codeCommandeClient") String code);

  @GetMapping(COMMANDECLIENT_ENDPOINT + "/commandesclients/all")
  ResponseEntity<List<CommandeClientDTO>> findAll();

  @GetMapping(COMMANDECLIENT_ENDPOINT + "/commandesclients/lignesCommande/{idCommande}")
  ResponseEntity<List<LigneCommandeClientDTO>> findAllLignesCommandesClientByCommandeClientId(@PathVariable("idCommande") Integer idCommande);

  @DeleteMapping(COMMANDECLIENT_ENDPOINT + "/commandesclients/delete/{idCommandeClient}")
  ResponseEntity<Void> delete(@PathVariable("idCommandeClient") Integer id);

}
