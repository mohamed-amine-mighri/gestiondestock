package com.amine.gestiondestock.services.servicImplimentation;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.amine.gestiondestock.DTO.MouvementDeStockDTO;
import com.amine.gestiondestock.Exception.ErrorCodes;
import com.amine.gestiondestock.Exception.InvalidEntityException;
import com.amine.gestiondestock.model.TypeMouvement;
import com.amine.gestiondestock.repos.MvtStkRepos;
import com.amine.gestiondestock.services.ArticleServ;
import com.amine.gestiondestock.services.MouvementStockServ;
import com.amine.gestiondestock.validator.MouvementStockV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class MouvementStockServImpl implements MouvementStockServ {

	  private MvtStkRepos repository;
	  private ArticleServ articleServ;

//	  @Autowired
//	  public MouvementStockServImpl(MvtStkRepos repository, ArticleServ articleService) {
//	    this.repository = repository;
//	    this.articleServ = articleService;
//	  }

	  @Override
	  public BigDecimal stockReelArticle(Integer idArticle) {
	    if (idArticle == null) {
	      log.warn("ID article is NULL");
	      return BigDecimal.valueOf(-1);
	    }
	    articleServ.findById(idArticle);
	    return repository.stockReelArticle(idArticle);
	  }

	  @Override
	  public List<MouvementDeStockDTO> mvtStkArticle(Integer idArticle) {
	    return repository.findAllByArticleId(idArticle).stream()
	        .map(MouvementDeStockDTO::fromEntity)
	        .collect(Collectors.toList());
	  }

	  @Override
	  public MouvementDeStockDTO entreeStock(MouvementDeStockDTO dto) {
	    return entreePositive(dto, TypeMouvement.ENTREE);
	  }

	  @Override
	  public MouvementDeStockDTO sortieStock(MouvementDeStockDTO dto) {
	    return sortieNegative(dto, TypeMouvement.SORTIE);
	  }

	  @Override
	  public MouvementDeStockDTO correctionStockPos(MouvementDeStockDTO dto) {
	    return entreePositive(dto, TypeMouvement.CORRECTION_POS);
	  }

	  @Override
	  public MouvementDeStockDTO correctionStockNeg(MouvementDeStockDTO dto) {
	    return sortieNegative(dto, TypeMouvement.CORRECTION_NEG);
	  }

	  private MouvementDeStockDTO entreePositive(MouvementDeStockDTO dto, TypeMouvement typeMvtStk) {
	    List<String> errors = MouvementStockV.validate(dto);
	    if (!errors.isEmpty()) {
	      log.error("Article is not valid {}", dto);
	      throw new InvalidEntityException("Le mouvement du stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
	    }
	    dto.setQuantite(
	        BigDecimal.valueOf(
	            Math.abs(dto.getQuantite().doubleValue())
	        )
	    );
	    dto.setType(typeMvtStk);
	    return MouvementDeStockDTO.fromEntity(
	        repository.save(MouvementDeStockDTO.toEntity(dto))
	    );
	  }

	  private MouvementDeStockDTO sortieNegative(MouvementDeStockDTO dto, TypeMouvement typeMvtStk) {
	    List<String> errors = MouvementStockV.validate(dto);
	    if (!errors.isEmpty()) {
	      log.error("Article is not valid {}", dto);
	      throw new InvalidEntityException("Le mouvement du stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
	    }
	    dto.setQuantite(
	        BigDecimal.valueOf(
	            Math.abs(dto.getQuantite().doubleValue()) * -1
	        )
	    );
	    dto.setType(typeMvtStk);
	    return MouvementDeStockDTO.fromEntity(
	        repository.save(MouvementDeStockDTO.toEntity(dto))
	    );
	  }
	}
