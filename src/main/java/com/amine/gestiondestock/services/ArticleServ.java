package com.amine.gestiondestock.services;

import java.util.List;

import com.amine.gestiondestock.DTO.ArticleDTO;


public interface ArticleServ {

	ArticleDTO save(ArticleDTO dto);

	ArticleDTO findById(Integer id);

	ArticleDTO findByCodeArticle(String codeArticle);

	List<ArticleDTO> findAll();

	List<ArticleDTO> findAllArticleByIdCategory(Integer idCategory);

	void delete(Integer id);
}
