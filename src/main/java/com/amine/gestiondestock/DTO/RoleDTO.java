package com.amine.gestiondestock.DTO;

import java.math.BigDecimal;

import com.amine.gestiondestock.model.Roles;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleDTO {

	  private Integer id;

	  private String roleName;
      
	  private UtilisateurDTO utilisateur;

	  public static RoleDTO fromEntity(Roles role) {
		    if (role == null) {
		      return null;
		    }
		    return RoleDTO.builder()
		        .id(role.getId())
		        .roleName(role.getRoleName())
		        .build();
		  }

		  public static Roles toEntity(RoleDTO roleDTO) {
		    if (roleDTO == null) {
		      return null;
		    }
		    Roles role = new Roles();
		    role.setId(roleDTO.getId());
		    role.setRoleName(roleDTO.getRoleName());
		    role.setUtilisateur(UtilisateurDTO.toEntity(roleDTO.getUtilisateur()));
		    return role;
		  }
}
