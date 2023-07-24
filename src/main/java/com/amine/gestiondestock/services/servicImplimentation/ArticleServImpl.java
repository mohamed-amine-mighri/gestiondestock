package com.amine.gestiondestock.services.servicImplimentation;

import java.util.List;
import java.util.stream.Collectors;


import com.amine.gestiondestock.DTO.ArticleDTO;
import com.amine.gestiondestock.DTO.LigneCommandeClientDTO;
import com.amine.gestiondestock.DTO.LigneCommandeFournisseurDTO;
import com.amine.gestiondestock.Exception.EntityNotFoundException;
import com.amine.gestiondestock.repos.ArticleRepos;
import com.amine.gestiondestock.repos.LigneCommandeClientRepos;
import com.amine.gestiondestock.repos.LigneCommandeFournisseurRepos;
import com.amine.gestiondestock.services.ArticleServ;
import com.amine.gestiondestock.validator.ArticleV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j

public abstract class ArticleServImpl implements ArticleServ {
	  
	private ArticleRepos articleRep;
	private LigneCommandeFournisseurRepos commandeFournisseurRep;
	private LigneCommandeClientRepos commandeClientRep;
	
	
	@Autowired
	  public ArticleServImpl(
	    ArticleRepos articleRep,
	    LigneCommandeClientRepos commandeClientRepo) {
	    this.articleRep = articleRep;
	    this.commandeFournisseurRep = commandeFournisseurRep;
	    this.commandeClientRep = commandeClientRep;
	  }
	
	
	@Override
	  public ArticleDTO save(ArticleDTO dto) {
	    List<String> errors = ArticleV.validate(dto);
	    if (!errors.isEmpty()) {
	      log.error("Article is not valid {}", dto);
	      //throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
	    }

	    return ArticleDTO.fromEntity(
	    		articleRep.save(
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

	    return articleRep.findById(id).map(ArticleDTO::fromEntity).orElseThrow(() ->
	        new EntityNotFoundException(
	            "Aucun article avec l'ID = " + id + " n' ete trouve dans la BDD"
	            )
	    );
	  }
	
	  @Override
	  public ArticleDTO findByCodeArticle(String codeArticle) {
	    if (!StringUtils.hasLength(codeArticle)) {
	      log.error("Article CODE is null");
	      return null;
	    }

	    return articleRep.findArticleByCodeArticle(codeArticle)
	        .map(ArticleDTO::fromEntity)
	        .orElseThrow(() ->
	            new EntityNotFoundException(
	                "Aucun article avec le CODE = " + codeArticle + " n' ete trouve dans la BDD"
	                )
	        );
	  }
	
	  @Override
	  public List<ArticleDTO> findAll() {
	    return articleRep.findAll().stream()
	        .map(ArticleDTO::fromEntity)
	        .collect(Collectors.toList());
	  }

	  @Override
	  public List<LigneCommandeClientDTO> findHistoriqueCommandeClient(Integer idArticle) {
	    return commandeClientRep.findAllByArticleId(idArticle).stream()
	        .map(LigneCommandeClientDTO::fromEntity)
	        .collect(Collectors.toList());
	  }

	  @Override
	  public List<LigneCommandeFournisseurDTO> findHistoriqueCommandeFournisseur(Integer idArticle) {
	    return commandeFournisseurRep.findAllByArticleId(idArticle).stream()
	        .map(LigneCommandeFournisseurDTO::fromEntity)
	        .collect(Collectors.toList());
	  }
	  
	  @Override
	  public List<ArticleDTO> findAllArticleByIdCategory(Integer idCategory) {
	    return articleRep.findAllById(idCategory).stream()
	        .map(ArticleDTO::fromEntity)
	        .collect(Collectors.toList());
	  }

	  @Override
	  public void delete(Integer id) {
	    if (id == null) {
	      log.error("Article ID is null");
	      return;
	    }

	    articleRep.deleteById(id);
	  }


		@Override
		  public ArticleDTO findByIdEntreprise(Integer id) {
		    if (id == null) {
		      log.error("Article ID is null");
		      return null;
		    }

		    return articleRep.findById(id).map(ArticleDTO::fromEntity).orElseThrow(() ->
		        new EntityNotFoundException(
		            "Aucun article avec l'ID = " + id + " n' ete trouve dans la BDD"
		            )
		    );
		  }
	
	
	
}
