package com.amine.gestiondestock.controller;


import com.amine.gestiondestock.DTO.MouvementDeStockDTO;
import com.amine.gestiondestock.controller.API.MvtStkApi;
import com.amine.gestiondestock.services.MouvementStockService;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MvtStkController implements MvtStkApi {

  private MouvementStockService service;

  @Autowired
  public MvtStkController(MouvementStockService service) {
    this.service = service;
  }

  @Override
  public BigDecimal stockReelArticle(Integer idArticle) {
    return service.stockReelArticle(idArticle);
  }

  @Override
  public List<MouvementDeStockDTO> mvtStkArticle(Integer idArticle) {
    return service.mvtStkArticle(idArticle);
  }

  @Override
  public MouvementDeStockDTO entreeStock(MouvementDeStockDTO dto) {
    return service.entreeStock(dto);
  }

  @Override
  public MouvementDeStockDTO sortieStock(MouvementDeStockDTO dto) {
    return service.sortieStock(dto);
  }

  @Override
  public MouvementDeStockDTO correctionStockPos(MouvementDeStockDTO dto) {
    return service.correctionStockPos(dto);
  }

  @Override
  public MouvementDeStockDTO correctionStockNeg(MouvementDeStockDTO dto) {
    return service.correctionStockNeg(dto);
  }
}
