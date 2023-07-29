package com.amine.gestiondestock.repos;

import com.amine.gestiondestock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepos extends JpaRepository<Client, Integer> {

//    Optional<Client> findClientByEmail(String email);
}
