package com.amine.gestiondestock.repos;

import com.amine.gestiondestock.model.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MvtStkRepos extends JpaRepository<MvtStk, Integer> {

    List<MvtStk> findAllByArticleId(Integer id);
}
