package com.amine.gestiondestock.controller;

import com.amine.gestiondestock.DTO.ArticleDTO;
import com.amine.gestiondestock.DTO.LigneCommandeClientDTO;
import com.amine.gestiondestock.DTO.LigneCommandeFournisseurDTO;
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
        System.out.printf("the ARTICLE ==> is",dto);
        return articleService.save(dto);
    }

    @Override
    public ArticleDTO findById(Integer id) {
        return articleService.findById(id);
    }


    @Override
    public ArticleDTO findByIdEntreprise(Integer id) {
        return articleService.findByIdEntreprise(id);
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
    public List<LigneCommandeClientDTO> findHistoriqueCommandeClient(Integer idArticle) {
        return articleService.findHistoriqueCommandeClient(idArticle);
    }

    @Override
    public List<LigneCommandeFournisseurDTO> findHistoriqueCommandeFournisseur(Integer idArticle) {
        return articleService.findHistoriqueCommandeFournisseur(idArticle);
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
