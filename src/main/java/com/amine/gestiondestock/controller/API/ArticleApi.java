package com.amine.gestiondestock.controller.API;

import com.amine.gestiondestock.DTO.ArticleDTO;


import io.swagger.annotations.Api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.amine.gestiondestock.utils.Constants.APP_ROOT;

@Api(APP_ROOT+"Articles")
public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/articles/create",  produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDTO save(@RequestBody ArticleDTO dto);

    @GetMapping(value = APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)

    ArticleDTO findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value = APP_ROOT + "/articles/filter/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)

    ArticleDTO findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(value = APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)

    List<ArticleDTO> findAll();

    @GetMapping(value = APP_ROOT + "/articles/filter/category/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDTO> findAllArticleByIdCategory(@PathVariable("idCategory") Integer idCategory);

    @DeleteMapping(value = APP_ROOT + "/articles/delete/{idArticle}")

    void delete(@PathVariable("idArticle") Integer id);

}

