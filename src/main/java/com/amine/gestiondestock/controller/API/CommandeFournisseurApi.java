package com.amine.gestiondestock.controller.API;

import com.amine.gestiondestock.DTO.CommandeFournisseurDTO;
import com.amine.gestiondestock.DTO.LigneCommandeFournisseurDTO;
import com.amine.gestiondestock.model.EtatCommande;
import io.swagger.annotations.Api;
import java.math.BigDecimal;
import java.util.List;
import static com.amine.gestiondestock.utils.Constants.CREATE_COMMANDE_FOURNISSEUR_ENDPOINT;
import static com.amine.gestiondestock.utils.Constants.COMMANDE_FOURNISSEUR_ENDPOINT;
import static com.amine.gestiondestock.utils.Constants.FIND_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT;
import static com.amine.gestiondestock.utils.Constants.FIND_COMMANDE_FOURNISSEUR_BY_CODE_ENDPOINT;
import static com.amine.gestiondestock.utils.Constants.FIND_ALL_COMMANDE_FOURNISSEUR_ENDPOINT;
import static com.amine.gestiondestock.utils.Constants.DELETE_COMMANDE_FOURNISSEUR_ENDPOINT;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api("commandefournisseur")
public interface CommandeFournisseurApi {

  @PostMapping(CREATE_COMMANDE_FOURNISSEUR_ENDPOINT)
  CommandeFournisseurDTO save(@RequestBody CommandeFournisseurDTO dto);

  @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/etat/{idCommande}/{etatCommande}")
  CommandeFournisseurDTO updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

  @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
  CommandeFournisseurDTO updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande,
      @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);

  @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/fournisseur/{idCommande}/{idFournisseur}")
  CommandeFournisseurDTO updateFournisseur(@PathVariable("idCommande") Integer idCommande, @PathVariable("idFournisseur") Integer idFournisseur);

  @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
  CommandeFournisseurDTO updateArticle(@PathVariable("idCommande") Integer idCommande,
      @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);

  @DeleteMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/delete/article/{idCommande}/{idLigneCommande}")
  CommandeFournisseurDTO deleteArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);

  @GetMapping(FIND_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT)
  CommandeFournisseurDTO findById(@PathVariable("idCommandeFournisseur") Integer id);

  @GetMapping(FIND_COMMANDE_FOURNISSEUR_BY_CODE_ENDPOINT)
  CommandeFournisseurDTO findByCode(@PathVariable("codeCommandeFournisseur") String code);

  @GetMapping(FIND_ALL_COMMANDE_FOURNISSEUR_ENDPOINT)
  List<CommandeFournisseurDTO> findAll();

  @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/lignesCommande/{idCommande}")
  List<LigneCommandeFournisseurDTO> findAllLignesCommandesFournisseurByCommandeFournisseurId(@PathVariable("idCommande") Integer idCommande);

  @DeleteMapping(DELETE_COMMANDE_FOURNISSEUR_ENDPOINT)
  void delete(@PathVariable("idCommandeFournisseur") Integer id);

}
