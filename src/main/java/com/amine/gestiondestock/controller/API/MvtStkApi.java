package com.amine.gestiondestock.controller.API;

import java.math.BigDecimal;
import java.util.List;

import com.amine.gestiondestock.DTO.MouvementDeStockDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static com.amine.gestiondestock.utils.Constants.APP_ROOT;


public interface MvtStkApi {

  @GetMapping(APP_ROOT + "/mvtstk/stockreel/{idArticle}")
  BigDecimal stockReelArticle(@PathVariable("idArticle") Integer idArticle);

  @GetMapping(APP_ROOT + "/mvtstk/filter/article/{idArticle}")
  List<MouvementDeStockDTO> mvtStkArticle(@PathVariable("idArticle") Integer idArticle);

  @PostMapping(APP_ROOT + "/mvtstk/entree")
  MouvementDeStockDTO entreeStock(@RequestBody MouvementDeStockDTO dto);

  @PostMapping(APP_ROOT + "/mvtstk/sortie")
  MouvementDeStockDTO sortieStock(@RequestBody MouvementDeStockDTO dto);

  @PostMapping(APP_ROOT + "/mvtstk/correctionpos")
  MouvementDeStockDTO correctionStockPos(@RequestBody MouvementDeStockDTO dto);

  @PostMapping(APP_ROOT + "/mvtstk/correctionneg")
  MouvementDeStockDTO correctionStockNeg(@RequestBody MouvementDeStockDTO dto);

}
