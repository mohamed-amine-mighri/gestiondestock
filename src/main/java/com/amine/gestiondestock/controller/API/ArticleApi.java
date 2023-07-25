package com.amine.gestiondestock.controller.API;

import com.amine.gestiondestock.DTO.ArticleDTO;
import com.amine.gestiondestock.DTO.LigneCommandeClientDTO;
import com.amine.gestiondestock.DTO.LigneCommandeFournisseurDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.amine.gestiondestock.utils.Constants.APP_ROOT;
@Api(APP_ROOT + "articles")
public interface ArticleApi {

    @PostMapping(value =  APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un article", notes = "Cette methode permet d'enregistrer ou modifier un article", response = ArticleDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article cree / modifie"),
            @ApiResponse(code = 400, message = "L'objet article n'est pas valide")
    })
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

