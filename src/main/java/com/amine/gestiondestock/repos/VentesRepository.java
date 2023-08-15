package com.amine.gestiondestock.repos;

import com.amine.gestiondestock.model.Utilisateur;
import com.amine.gestiondestock.model.Ventes;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {

  Optional<Ventes> findVentesByCode(String code);
  @Query(value = "select v from Ventes v where v.id = :idVente")
  Optional<Ventes> findById(@Param("idVente") String idVente);

}
