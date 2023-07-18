package com.amine.gestiondestock.repos;

import com.amine.gestiondestock.model.Article;
import com.amine.gestiondestock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepos extends JpaRepository<Article, Integer> {

    Optional<Article> findArticleByCodeArticle(String codeArticle);

    List<Article> findAllById(Integer idCategory);

    //Optional<Article> findByIdEntreprise(Entreprise id);

}
