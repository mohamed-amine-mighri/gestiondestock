package com.amine.gestiondestock.controller.API;

import static com.amine.gestiondestock.utils.Constants.APP_ROOT;


import com.amine.gestiondestock.DTO.MouvementDeStockDTO;
import io.swagger.annotations.Api;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api("mvtstk")
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
