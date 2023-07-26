package com.amine.gestiondestock.services.servicImplimentation;


import java.util.List;
import java.util.stream.Collectors;

import com.amine.gestiondestock.DTO.ArticleDTO;
import com.amine.gestiondestock.DTO.LigneCommandeClientDTO;
import com.amine.gestiondestock.DTO.LigneCommandeFournisseurDTO;
import com.amine.gestiondestock.DTO.LigneVenteDTO;
import com.amine.gestiondestock.Exception.EntityNotFoundException;
import com.amine.gestiondestock.Exception.ErrorCodes;
import com.amine.gestiondestock.Exception.InvalidEntityException;
import com.amine.gestiondestock.Exception.InvalidOperationException;
import com.amine.gestiondestock.model.LigneCommandeClient;
import com.amine.gestiondestock.model.LigneCommandeFournisseur;
import com.amine.gestiondestock.model.LigneVente;
import com.amine.gestiondestock.repos.ArticleRepos;
import com.amine.gestiondestock.repos.LigneCommandeClientRepos;
import com.amine.gestiondestock.repos.LigneCommandeFournisseurRepos;
import com.amine.gestiondestock.repos.LigneVenteRepository;
import com.amine.gestiondestock.services.ArticleServ;
import com.amine.gestiondestock.validator.ArticleV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
@Slf4j
public class ArticleServiceImpl implements ArticleServ {

  private ArticleRepos articleRepository;
  private LigneVenteRepository venteRepository;
  private LigneCommandeFournisseurRepos commandeFournisseurRepository;
  private LigneCommandeClientRepos commandeClientRepository;

  @Autowired
  public ArticleServiceImpl(
      ArticleRepos articleRepository,
      LigneVenteRepository venteRepository, LigneCommandeFournisseurRepos commandeFournisseurRepository,
      LigneCommandeClientRepos commandeClientRepository) {
    this.articleRepository = articleRepository;
    this.venteRepository = venteRepository;
    this.commandeFournisseurRepository = commandeFournisseurRepository;
    this.commandeClientRepository = commandeClientRepository;
  }

  @Override
  public ArticleDTO save(ArticleDTO dto) {
    List<String> errors = ArticleV.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Article is not valid {}", dto);
      throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
    }

    return ArticleDTO.fromEntity(
        articleRepository.save(
            ArticleDTO.toEntity(dto)
        )
    );
  }

  @Override
  public ArticleDTO findById(Integer id) {
    if (id == null) {
      log.error("Article ID is null");
      return null;
    }

    return articleRepository.findById(id).map(ArticleDTO::fromEntity).orElseThrow(() ->
        new EntityNotFoundException(
            "Aucun article avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.ARTICLE_NOT_FOUND)
    );
  }

  @Override
  public ArticleDTO findByCodeArticle(String codeArticle) {
    if (!StringUtils.hasLength(codeArticle)) {
      log.error("Article CODE is null");
      return null;
    }

    return articleRepository.findArticleByCodeArticle(codeArticle)
        .map(ArticleDTO::fromEntity)
        .orElseThrow(() ->
            new EntityNotFoundException(
                "Aucun article avec le CODE = " + codeArticle + " n' ete trouve dans la BDD",
                ErrorCodes.ARTICLE_NOT_FOUND)
        );
  }

  @Override
  public List<ArticleDTO> findAll() {
    return articleRepository.findAll().stream()
        .map(ArticleDTO::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public List<LigneVenteDTO> findHistoriqueVentes(Integer idArticle) {
    return venteRepository.findAllByArticleId(idArticle).stream()
        .map(LigneVenteDTO::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public List<LigneCommandeClientDTO> findHistoriaueCommandeClient(Integer idArticle) {
    return commandeClientRepository.findAllByArticleId(idArticle).stream()
        .map(LigneCommandeClientDTO::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public List<LigneCommandeFournisseurDTO> findHistoriqueCommandeFournisseur(Integer idArticle) {
    return commandeFournisseurRepository.findAllByArticleId(idArticle).stream()
        .map(LigneCommandeFournisseurDTO::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public List<ArticleDTO> findAllArticleByIdCategory(Integer idCategory) {
    return articleRepository.findAllById(idCategory).stream()
        .map(ArticleDTO::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Article ID is null");
      return;
    }
    List<LigneCommandeClient> ligneCommandeClients = commandeClientRepository.findAllByArticleId(id);
    if (!ligneCommandeClients.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer un article deja utilise dans des commandes client", ErrorCodes.ARTICLE_ALREADY_IN_USE);
    }
    List<LigneCommandeFournisseur> ligneCommandeFournisseurs = commandeFournisseurRepository.findAllByArticleId(id);
    if (!ligneCommandeFournisseurs.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer un article deja utilise dans des commandes fournisseur",
          ErrorCodes.ARTICLE_ALREADY_IN_USE);
    }
    List<LigneVente> ligneVentes = venteRepository.findAllByArticleId(id);
    if (!ligneVentes.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer un article deja utilise dans des ventes",
          ErrorCodes.ARTICLE_ALREADY_IN_USE);
    }
    articleRepository.deleteById(id);
  }
}
