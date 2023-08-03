package com.amine.gestiondestock.controller;

import com.amine.gestiondestock.DTO.ArticleDTO;
import com.amine.gestiondestock.controller.API.ArticleApi;
import com.amine.gestiondestock.services.ArticleServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class ArticleController implements ArticleApi {

    private ArticleServ articleService;

    @Autowired
    public ArticleController(
            ArticleServ articleService
    ) {
        this.articleService = articleService;
    }

    @Override
    public ArticleDTO save(ArticleDTO dto) {
        return articleService.save(dto);
    }

    @Override
    public ArticleDTO findById(Integer id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDTO findByCodeArticle(String codeArticle) {
        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDTO> findAll() {
        return articleService.findAll();
    }


    @Override
    public List<ArticleDTO> findAllArticleByIdCategory(Integer idCategory) {
        return articleService.findAllArticleByIdCategory(idCategory);
    }

    @Override
    public void delete(Integer id) {
        articleService.delete(id);
    }
}
