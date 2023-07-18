package com.amine.gestiondestock.repos;

import com.amine.gestiondestock.model.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeFournisseurRepos extends JpaRepository<LigneCommandeFournisseur, Integer> {

    List<LigneCommandeFournisseur> findAllByCommandeFournisseurId(Integer id);

    List<LigneCommandeFournisseur> findAllByArticleId(Integer id);
}
