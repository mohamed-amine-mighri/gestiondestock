package com.amine.gestiondestock.repos;

import com.amine.gestiondestock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeClientRepos extends JpaRepository<CommandeClient, Integer> {

    Optional<CommandeClient> findCommandeClientByCodeCommande(String code);

    List<CommandeClient> findAllByClientId(Integer id);
}
