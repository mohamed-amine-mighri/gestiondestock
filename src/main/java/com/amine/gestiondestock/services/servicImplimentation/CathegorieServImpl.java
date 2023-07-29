package com.amine.gestiondestock.services.servicImplimentation;

import java.util.List;
import java.util.stream.Collectors;


import com.amine.gestiondestock.DTO.CategorieDTO;
import com.amine.gestiondestock.Exception.EntityNotFoundException;
import com.amine.gestiondestock.model.Article;
import com.amine.gestiondestock.repos.ArticleRepos;
import com.amine.gestiondestock.repos.CategoryRepos;
import com.amine.gestiondestock.services.CathegorieServ;
import com.amine.gestiondestock.validator.CategorieV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CathegorieServImpl implements CathegorieServ {
	
	  private CategoryRepos cathegorieRep;
	  private ArticleRepos articleRep;

	  @Autowired
	  public CathegorieServImpl(CategoryRepos cathegorieRep, ArticleRepos articleRep) {
	    this.cathegorieRep = cathegorieRep;
	    this.articleRep = articleRep;
	  }
	  
	  
	  @Override
	  public CategorieDTO save(CategorieDTO dto) {
	    List<String> errors = CategorieV.validate(dto);
	    if (!errors.isEmpty()) {
	      log.error("Article is not valid {}", dto);
	      //throw new InvalidEntityException("La category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
	    }
	    return CategorieDTO.fromEntity(
	    		cathegorieRep.save(CategorieDTO.toEntity(dto))
	    );
	  }
	
	

	  @Override
	  public CategorieDTO findById(Integer id) {
	    if (id == null) {
	      log.error("Category ID is null");
	      return null;
	    }
	    return cathegorieRep.findById(id)
	        .map(CategorieDTO::fromEntity)
	        .orElseThrow(() -> new EntityNotFoundException(
	            "Aucune category avec l'ID = " + id + " n' ete trouve dans la BDD"
	            )
	        );
	  }

	
	  
	  @Override
	  public List<CategorieDTO> findAll() {
	    return cathegorieRep.findAll().stream()
	        .map(CategorieDTO::fromEntity)
	        .collect(Collectors.toList());
	  }

	  @Override
	  public void delete(Integer id) {
	    if (id == null) {
	      log.error("Category ID is null");
	      return;
	    }
	    List<Article> articles = articleRep.findAllByCategoryId(id);
	    if (!articles.isEmpty()) {
	      log.error("Impossible de supprimer cette categorie qui est deja utilise");
	    }
	    cathegorieRep.deleteById(id);
	  }
	  
	
}
