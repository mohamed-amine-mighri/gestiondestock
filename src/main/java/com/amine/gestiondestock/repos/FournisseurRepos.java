package com.amine.gestiondestock.repos;

import com.amine.gestiondestock.model.Client;
import com.amine.gestiondestock.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FournisseurRepos extends JpaRepository<Fournisseur, Integer> {

    //Optional<Fournisseur> findFournisseurByEmail(String email);
}
