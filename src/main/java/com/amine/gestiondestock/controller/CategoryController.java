package com.amine.gestiondestock.controller;


import java.util.List;

import com.amine.gestiondestock.DTO.CategorieDTO;
import com.amine.gestiondestock.controller.API.CategoryApi;
import com.amine.gestiondestock.services.CathegorieServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController implements CategoryApi {

  private CathegorieServ categoryService;

  @Autowired
  public CategoryController(CathegorieServ categoryService) {
    this.categoryService = categoryService;
  }

  @Override
  public CategorieDTO save(CategorieDTO dto) {
    return categoryService.save(dto);
  }

  @Override
  public CategorieDTO findById(Integer idCategory) {
    return categoryService.findById(idCategory);
  }

  @Override
  public List<CategorieDTO> findAll() {
    return categoryService.findAll();
  }

  @Override
  public void delete(Integer id) {
    categoryService.delete(id);
  }
}
