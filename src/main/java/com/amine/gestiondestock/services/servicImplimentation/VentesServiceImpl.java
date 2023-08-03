package com.amine.gestiondestock.services.servicImplimentation;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.amine.gestiondestock.DTO.ArticleDTO;
import com.amine.gestiondestock.DTO.LigneVenteDTO;
import com.amine.gestiondestock.DTO.MouvementDeStockDTO;
import com.amine.gestiondestock.DTO.VentesDTO;
import com.amine.gestiondestock.Exception.EntityNotFoundException;
import com.amine.gestiondestock.Exception.ErrorCodes;
import com.amine.gestiondestock.Exception.InvalidEntityException;
import com.amine.gestiondestock.Exception.InvalidOperationException;
import com.amine.gestiondestock.model.*;
import com.amine.gestiondestock.repos.ArticleRepos;
import com.amine.gestiondestock.repos.LigneVenteRepository;
import com.amine.gestiondestock.repos.VentesRepository;
import com.amine.gestiondestock.services.MouvementStockService;
import com.amine.gestiondestock.services.VentesService;
import com.amine.gestiondestock.validator.VentesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

  private ArticleRepos articleRepository;
  private VentesRepository ventesRepository;
  private LigneVenteRepository ligneVenteRepository;
  private MouvementStockService mvtStkService;

  @Autowired
  public VentesServiceImpl(ArticleRepos articleRepository, VentesRepository ventesRepository,
      LigneVenteRepository ligneVenteRepository, MouvementStockService mvtStkService) {
    this.articleRepository = articleRepository;
    this.ventesRepository = ventesRepository;
    this.ligneVenteRepository = ligneVenteRepository;
    this.mvtStkService = mvtStkService;
  }

  @Override
  public VentesDTO save(VentesDTO dto) {
    List<String> errors = VentesValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Ventes n'est pas valide");
      throw new InvalidEntityException("L'objet vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
    }

    List<String> articleErrors = new ArrayList<>();

    dto.getLigneVentes().forEach(ligneVenteDto -> {
      Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
      if (article.isEmpty()) {
        articleErrors.add("Aucun article avec l'ID " + ligneVenteDto.getArticle().getId() + " n'a ete trouve dans la BDD");
      }
    });

    if (!articleErrors.isEmpty()) {
      log.error("One or more articles were not found in the DB, {}", errors);
      throw new InvalidEntityException("Un ou plusieurs articles n'ont pas ete trouve dans la BDD", ErrorCodes.VENTE_NOT_VALID, errors);
    }

    Ventes savedVentes = ventesRepository.save(VentesDTO.toEntity(dto));

    dto.getLigneVentes().forEach(ligneVenteDto -> {
      LigneVente ligneVente = LigneVenteDTO.toEntity(ligneVenteDto);
      ligneVente.setVente(savedVentes);
      ligneVenteRepository.save(ligneVente);
      updateMvtStk(ligneVente);
    });

    return VentesDTO.fromEntity(savedVentes);
  }

  @Override
  public VentesDTO findById(Integer id) {
    if (id == null) {
      log.error("Ventes ID is NULL");
      return null;
    }
    return ventesRepository.findById(id)
        .map(VentesDTO::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException("Aucun vente n'a ete trouve dans la BDD", ErrorCodes.VENTE_NOT_FOUND));
  }

  @Override
  public VentesDTO findByCode(String code) {
    if (!StringUtils.hasLength(code)) {
      log.error("Vente CODE is NULL");
      return null;
    }
    return ventesRepository.findVentesByCode(code)
        .map(VentesDTO::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucune vente client n'a ete trouve avec le CODE " + code, ErrorCodes.VENTE_NOT_VALID
        ));
  }

  @Override
  public List<VentesDTO> findAll() {
    return ventesRepository.findAll().stream()
        .map(VentesDTO::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Vente ID is NULL");
      return;
    }
    List<LigneVente> ligneVentes = ligneVenteRepository.findAllByVenteId(id);
    if (!ligneVentes.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer une vente ...",
          ErrorCodes.VENTE_ALREADY_IN_USE);
    }
    ventesRepository.deleteById(id);
  }

  private void updateMvtStk(LigneVente lig) {
    MouvementDeStockDTO mvtStkDto = MouvementDeStockDTO.builder()
        .article(ArticleDTO.fromEntity(lig.getArticle()))
        .date(Instant.now())
        .type(TypeMouvement.SORTIE)
        .sourceMouvement(SourceMouvement.VENTE)
        .quantite(lig.getQuantite())
        .idEntreprise(lig.getIdEntreprise())
        .build();
    mvtStkService.sortieStock(mvtStkDto);
  }
}
