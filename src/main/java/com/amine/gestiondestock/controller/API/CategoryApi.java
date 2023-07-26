package com.amine.gestiondestock.controller.API;


import static com.amine.gestiondestock.utils.Constants.APP_ROOT;

import com.amine.gestiondestock.DTO.CategorieDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface CategoryApi {

	@PostMapping(value = APP_ROOT
			+ "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	CategorieDTO save(@RequestBody CategorieDTO dto);

	@GetMapping(value = APP_ROOT + "/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)

	CategorieDTO findById(@PathVariable("idCategory") Integer idCategory);


	@GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
	List<CategorieDTO> findAll();

	@DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}")
	void delete(@PathVariable("idCategory") Integer id);

}
