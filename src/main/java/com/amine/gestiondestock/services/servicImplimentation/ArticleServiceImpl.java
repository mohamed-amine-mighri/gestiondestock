package com.amine.gestiondestock.services.servicImplimentation;


import java.util.List;
import java.util.stream.Collectors;

import com.amine.gestiondestock.DTO.ArticleDTO;
import com.amine.gestiondestock.Exception.EntityNotFoundException;
import com.amine.gestiondestock.Exception.ErrorCodes;
import com.amine.gestiondestock.Exception.InvalidEntityException;
import com.amine.gestiondestock.repos.ArticleRepos;
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


  @Autowired
  public ArticleServiceImpl(
      ArticleRepos articleRepository) {
    this.articleRepository = articleRepository;
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

    return articleRepository.findById(id)
            .map(ArticleDTO::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException(
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
  public List<ArticleDTO> findAllArticleByIdCategory(Integer idCategory) {
    return articleRepository.findAllByCategoryId(idCategory).stream()
        .map(ArticleDTO::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Article ID is null");
      return;
    }
    articleRepository.deleteById(id);
  }
}
