package com.amine.gestiondestock.controller;

import com.amine.gestiondestock.DTO.ArticleDTO;
import com.amine.gestiondestock.DTO.LigneCommandeClientDTO;
import com.amine.gestiondestock.DTO.LigneCommandeFournisseurDTO;
import com.amine.gestiondestock.DTO.LigneVenteDTO;
import com.amine.gestiondestock.controller.API.ArticleApi;
import com.amine.gestiondestock.services.servicImplimentation.ArticleServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.amine.gestiondestock.utils.Constants.APP_ROOT;

import java.util.List;

@RestController

public class ArticleController implements ArticleApi {

    private ArticleServiceImpl articleService;

    @Autowired
    public ArticleController(
            ArticleServiceImpl articleService
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
    public List<LigneVenteDTO> findHistoriqueVentes(Integer idArticle) {
        return articleService.findHistoriqueVentes(idArticle);
    }

    @Override
    public List<LigneCommandeClientDTO> findHistoriaueCommandeClient(Integer idArticle) {
        return articleService.findHistoriaueCommandeClient(idArticle);
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
