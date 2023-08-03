package com.amine.gestiondestock.controller.API;

import static com.amine.gestiondestock.utils.Constants.VENTES_ENDPOINT;

import com.amine.gestiondestock.DTO.VentesDTO;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api("ventes")
public interface VentesApi {

  @PostMapping(VENTES_ENDPOINT + "/create")
  VentesDTO save(@RequestBody VentesDTO dto);

  @GetMapping(VENTES_ENDPOINT + "/{idVente}")
  VentesDTO findById(@PathVariable("idVente") Integer id);

  @GetMapping(VENTES_ENDPOINT + "/{codeVente}")
  VentesDTO findByCode(@PathVariable("codeVente") String code);

  @GetMapping(VENTES_ENDPOINT + "/all")
  List<VentesDTO> findAll();

  @DeleteMapping(VENTES_ENDPOINT + "/delete/{idVente}")
  void delete(@PathVariable("idVente") Integer id);

}
