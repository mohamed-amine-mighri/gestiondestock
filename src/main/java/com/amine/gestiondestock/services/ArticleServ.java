package com.amine.gestiondestock.services;

import java.util.List;

import com.amine.gestiondestock.DTO.ArticleDTO;
import com.amine.gestiondestock.DTO.LigneCommandeClientDTO;
import com.amine.gestiondestock.DTO.LigneCommandeFournisseurDTO;

public interface ArticleServ {

	ArticleDTO save(ArticleDTO dto);

	ArticleDTO findById(Integer id);

	ArticleDTO findByCodeArticle(String codeArticle);

	List<ArticleDTO> findAll();

	List<LigneCommandeClientDTO> findHistoriqueCommandeClient(Integer idArticle);

    List<LigneCommandeFournisseurDTO> findHistoriqueCommandeFournisseur(Integer idArticle);
	
	void delete(Integer id);

	List<ArticleDTO> findAllArticleByIdCategory(Integer idCategory);

	ArticleDTO findByIdEntreprise(Integer id);
}
