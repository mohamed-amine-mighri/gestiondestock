package com.amine.gestiondestock.controller.API;

import com.amine.gestiondestock.DTO.ArticleDTO;
import com.amine.gestiondestock.DTO.LigneCommandeClientDTO;
import com.amine.gestiondestock.DTO.LigneCommandeFournisseurDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.amine.gestiondestock.utils.Constants.APP_ROOT;

public interface ArticleApi {

    @PostMapping(value =  APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDTO save(@RequestBody ArticleDTO dto);

    @GetMapping(value = APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)

    ArticleDTO findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value = APP_ROOT + "/articles/filter/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)

    ArticleDTO findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(value = APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDTO> findAll();

    @GetMapping(value = APP_ROOT + "/articles/historique/commandeclient/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<LigneCommandeClientDTO> findHistoriqueCommandeClient(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(value = APP_ROOT + "/articles/historique/commandefournisseur/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<LigneCommandeFournisseurDTO> findHistoriqueCommandeFournisseur(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(value = APP_ROOT + "/articles/filter/category/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDTO> findAllArticleByIdCategory(@PathVariable("idCategory") Integer idCategory);

    @DeleteMapping(value = APP_ROOT + "/articles/delete/{idArticle}")

    ArticleDTO findByIdEntreprise(@PathVariable("idEntreprise") Integer id);

    @GetMapping(value = APP_ROOT + "/articles/filter/{idEntreprise}", produces = MediaType.APPLICATION_JSON_VALUE)

    void delete(@PathVariable("idArticle") Integer id);



}

