package com.amine.gestiondestock.services;

import java.util.List;

import com.amine.gestiondestock.DTO.ArticleDTO;
import com.amine.gestiondestock.DTO.LigneCommandeClientDTO;
import com.amine.gestiondestock.DTO.LigneCommandeFournisseurDTO;
import com.amine.gestiondestock.DTO.LigneVenteDTO;


public interface ArticleServ {

	ArticleDTO save(ArticleDTO dto);

	ArticleDTO findById(Integer id);
  
	ArticleDTO findByCodeArticle(String codeArticle);
  
	List<ArticleDTO> findAll();
  
	List<LigneVenteDTO> findHistoriqueVentes(Integer idArticle);
  
	List<LigneCommandeClientDTO> findHistoriaueCommandeClient(Integer idArticle);
  
	List<LigneCommandeFournisseurDTO> findHistoriqueCommandeFournisseur(Integer idArticle);
  
	List<ArticleDTO> findAllArticleByIdCategory(Integer idCategory);
  
	void delete(Integer id);
}
