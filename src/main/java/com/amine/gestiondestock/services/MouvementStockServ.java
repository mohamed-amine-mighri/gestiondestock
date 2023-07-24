package com.amine.gestiondestock.services;

import com.amine.gestiondestock.DTO.MouvementDeStockDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface MouvementStockServ {

	  BigDecimal stockReelArticle(Integer idArticle);

	  List<MouvementDeStockDTO> mvtStkArticle(Integer idArticle);

	  MouvementDeStockDTO entreeStock(MouvementDeStockDTO dto);

	  MouvementDeStockDTO sortieStock(MouvementDeStockDTO dto);

	  MouvementDeStockDTO correctionStockPos(MouvementDeStockDTO dto);

	  MouvementDeStockDTO correctionStockNeg(MouvementDeStockDTO dto);
	
	
}
