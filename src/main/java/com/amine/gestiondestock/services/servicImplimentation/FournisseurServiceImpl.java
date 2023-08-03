package com.amine.gestiondestock.services.servicImplimentation;


import java.util.List;
import java.util.stream.Collectors;

import com.amine.gestiondestock.DTO.FournisseurDTO;
import com.amine.gestiondestock.Exception.EntityNotFoundException;
import com.amine.gestiondestock.Exception.ErrorCodes;
import com.amine.gestiondestock.Exception.InvalidEntityException;
import com.amine.gestiondestock.Exception.InvalidOperationException;
import com.amine.gestiondestock.model.CommandeClient;
import com.amine.gestiondestock.model.CommandeFournisseur;
import com.amine.gestiondestock.repos.CommandeFournisseurRepos;
import com.amine.gestiondestock.repos.FournisseurRepos;
import com.amine.gestiondestock.services.FournisseurService;
import com.amine.gestiondestock.validator.FournisseurV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

  private FournisseurRepos fournisseurRepository;
  private CommandeFournisseurRepos commandeFournisseurRepository;

  @Autowired
  public FournisseurServiceImpl(FournisseurRepos fournisseurRepository,
      CommandeFournisseurRepos commandeFournisseurRepository) {
    this.fournisseurRepository = fournisseurRepository;
    this.commandeFournisseurRepository = commandeFournisseurRepository;
  }

  @Override
  public FournisseurDTO save(FournisseurDTO dto) {
    List<String> errors = FournisseurV.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Fournisseur is not valid {}", dto);
      throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
    }

    return FournisseurDTO.fromEntity(
        fournisseurRepository.save(
            FournisseurDTO.toEntity(dto)
        )
    );
  }

  @Override
  public FournisseurDTO findById(Integer id) {
    if (id == null) {
      log.error("Fournisseur ID is null");
      return null;
    }
    return fournisseurRepository.findById(id)
        .map(FournisseurDTO::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucun fournisseur avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.FOURNISSEUR_NOT_FOUND)
        );
  }

  @Override
  public List<FournisseurDTO> findAll() {
    return fournisseurRepository.findAll().stream()
        .map(FournisseurDTO::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Fournisseur ID is null");
      return;
    }
    List<CommandeFournisseur> commandeFournisseur = commandeFournisseurRepository.findAllByFournisseurId(id);
    if (!commandeFournisseur.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer un fournisseur qui a deja des commandes",
          ErrorCodes.FOURNISSEUR_ALREADY_IN_USE);
    }
    fournisseurRepository.deleteById(id);
  }
}
