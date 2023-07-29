package com.amine.gestiondestock.DTO;

import java.math.BigDecimal;

import com.amine.gestiondestock.model.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleDTO {

	private Integer id;

	private String roleName;

	@JsonIgnore
	private UtilisateurDTO utilisateur;

	public static RoleDTO fromEntity(Roles roles) {
		if (roles == null) {
			return null;
		}
		return RoleDTO.builder()
				.id(roles.getId())
				.roleName("ADMIN")
				.build();
	}

	public static Roles toEntity(RoleDTO dto) {
		if (dto == null) {
			return null;
		}
		Roles roles = new Roles();
		roles.setId(dto.getId());
		roles.setRoleName(dto.getRoleName());
		roles.setUtilisateur(UtilisateurDTO.toEntity(dto.getUtilisateur()));
		return roles;
	}

}
