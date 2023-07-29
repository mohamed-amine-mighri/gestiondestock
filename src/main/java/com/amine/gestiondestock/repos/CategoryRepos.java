package com.amine.gestiondestock.repos;

import com.amine.gestiondestock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepos extends JpaRepository<Category, Integer> {

    Optional<Category> findCategoryByCode(String code);

}
