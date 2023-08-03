package com.amine.gestiondestock.services;


import com.amine.gestiondestock.DTO.MouvementDeStockDTO;

import java.math.BigDecimal;
import java.util.List;

public interface MouvementStockService {
    BigDecimal stockReelArticle(Integer idArticle);

    List<MouvementDeStockDTO> mvtStkArticle(Integer idArticle);

    MouvementDeStockDTO entreeStock(MouvementDeStockDTO dto);

    MouvementDeStockDTO sortieStock(MouvementDeStockDTO dto);

    MouvementDeStockDTO correctionStockPos(MouvementDeStockDTO dto);

    MouvementDeStockDTO correctionStockNeg(MouvementDeStockDTO dto);

}
