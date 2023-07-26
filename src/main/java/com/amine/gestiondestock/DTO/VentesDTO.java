package com.amine.gestiondestock.DTO;

import java.time.Instant;
import java.util.List;

import com.amine.gestiondestock.model.Ventes;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VentesDTO {

  private Integer id;

  private String code;

  private Instant dateVente;

  private String commentaire;

  private List<LigneVenteDTO> ligneVentes;

  private Integer idEntreprise;

  public static VentesDTO fromEntity(Ventes vente) {
    if (vente == null) {
      return null;
    }
    return VentesDTO.builder()
        .id(vente.getId())
        .code(vente.getCode())
        .commentaire(vente.getCommentaire())
        .idEntreprise(vente.getIdEntreprise())
        .build();
  }

  public static Ventes toEntity(VentesDTO dto) {
    if (dto == null) {
      return null;
    }
    Ventes ventes = new Ventes();
    ventes.setId(dto.getId());
    ventes.setCode(ventes.getCode());
    ventes.setCommentaire(dto.getCommentaire());
    ventes.setIdEntreprise(dto.getIdEntreprise());
    return ventes;
  }
}
