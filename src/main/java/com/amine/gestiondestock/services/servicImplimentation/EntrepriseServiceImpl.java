package com.amine.gestiondestock.services.servicImplimentation;


import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.amine.gestiondestock.DTO.EntrepriseDTO;
import com.amine.gestiondestock.DTO.RoleDTO;
import com.amine.gestiondestock.DTO.UtilisateurDTO;
import com.amine.gestiondestock.Exception.EntityNotFoundException;
import com.amine.gestiondestock.Exception.ErrorCodes;
import com.amine.gestiondestock.Exception.InvalidEntityException;
import com.amine.gestiondestock.repos.EntrepriseRepos;
import com.amine.gestiondestock.repos.RolesRepository;
import com.amine.gestiondestock.services.EntrepriseService;
import com.amine.gestiondestock.services.UtilisateurService;
import com.amine.gestiondestock.validator.EntrepriseV;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

  private EntrepriseRepos entrepriseRepository;
  private UtilisateurService utilisateurService;
  private RolesRepository rolesRepository;

  @Autowired
  public EntrepriseServiceImpl(EntrepriseRepos entrepriseRepository, UtilisateurService utilisateurService,
      RolesRepository rolesRepository) {
    this.entrepriseRepository = entrepriseRepository;
    this.utilisateurService = utilisateurService;
    this.rolesRepository = rolesRepository;
  }

  @Override
  public EntrepriseDTO save(EntrepriseDTO dto) {
    List<String> errors = EntrepriseV.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Entreprise is not valid {}", dto);
      throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
    }
    EntrepriseDTO savedEntreprise = EntrepriseDTO.fromEntity(
        entrepriseRepository.save(EntrepriseDTO.toEntity(dto))
    );
//
//    UtilisateurDTO utilisateur = fromEntreprise(savedEntreprise);
//
//    UtilisateurDTO savedUser = utilisateurService.save(utilisateur);
//
//    RoleDTO rolesDto = RoleDTO.builder()
//        .roleName("ADMIN")
//        .utilisateur(savedUser)
//        .build();
//
//    rolesRepository.save(RoleDTO.toEntity(rolesDto));

    return  savedEntreprise;
  }
//
//  private UtilisateurDTO fromEntreprise(EntrepriseDTO dto) {
//    return UtilisateurDTO.builder()
//        .adresse(dto.getAdresse())
//        .nom(dto.getNom())
//        .email(dto.getEmail())
//        .moteDePasse(generateRandomPassword())
//        .entreprise(dto)
//        .dateDeNaissance(Instant.now())
//        .photo(dto.getPhoto())
//        .build();
//  }
//
//  private String generateRandomPassword() {
//    return "som3R@nd0mP@$$word";
//  }

  @Override
  public EntrepriseDTO findById(Integer id) {
    if (id == null) {
      log.error("Entreprise ID is null");
      return null;
    }
    return entrepriseRepository.findById(id)
        .map(EntrepriseDTO::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucune entreprise avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.ENTREPRISE_NOT_FOUND)
        );
  }

  @Override
  public List<EntrepriseDTO> findAll() {
    return entrepriseRepository.findAll().stream()
        .map(EntrepriseDTO::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Entreprise ID is null");
      return;
    }
    entrepriseRepository.deleteById(id);
  }
}
