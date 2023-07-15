package com.amine.gestiondestock.DTO;



import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChPassWordDTO {

  private Integer id;

  private String motDePasse;

  private String confirmMotDePasse;

}
