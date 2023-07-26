package com.amine.gestiondestock.repos;

import java.util.List;

import com.amine.gestiondestock.model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer> {

  List<LigneVente> findAllByArticleId(Integer idArticle);

  List<LigneVente> findAllByVenteId(Integer id);
}
