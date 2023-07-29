package com.amine.gestiondestock.repos;

import com.amine.gestiondestock.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository<S extends Roles> extends JpaRepository<Roles, Integer> {

}
