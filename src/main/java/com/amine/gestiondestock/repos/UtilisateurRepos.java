package com.amine.gestiondestock.repos;

import com.amine.gestiondestock.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepos extends JpaRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findUtilisateurByEmail(String email);
}
