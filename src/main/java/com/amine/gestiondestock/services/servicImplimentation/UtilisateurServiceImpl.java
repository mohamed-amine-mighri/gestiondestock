package com.amine.gestiondestock.services.servicImplimentation;

import com.amine.gestiondestock.DTO.UtilisateurDTO;
import com.amine.gestiondestock.Exception.EntityNotFoundException;
import com.amine.gestiondestock.Exception.ErrorCodes;
import com.amine.gestiondestock.Exception.InvalidEntityException;
import com.amine.gestiondestock.model.Utilisateur;
import com.amine.gestiondestock.repos.UtilisateurRepos;
import com.amine.gestiondestock.services.UtilisateurService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.amine.gestiondestock.validator.UtilisateurV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;


@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

  private UtilisateurRepos utilisateurRepository;
  private final PasswordEncoder passwordEncoder;


  @Autowired
  public UtilisateurServiceImpl(UtilisateurRepos utilisateurRepository,
                                PasswordEncoder passwordEncoder) {
    this.utilisateurRepository = utilisateurRepository;
    //this.passwordEncoder = passwordEncoder;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UtilisateurDTO save(UtilisateurDTO dto) {
    List<String> errors = UtilisateurV.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Utilisateur is not valid {}", dto);
      throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
    }

    if(userAlreadyExists(dto.getEmail())) {
      log.error("Utilisateur is not valid {}", dto);
      throw new InvalidEntityException("Un autre utilisateur avec le meme email existe deja", ErrorCodes.UTILISATEUR_ALREADY_EXISTS,
          Collections.singletonList("Un autre utilisateur avec le meme email existe deja dans la BDD"));
    }

    dto.setMoteDePasse(passwordEncoder.encode(dto.getMoteDePasse()));

    return UtilisateurDTO.fromEntity(
        utilisateurRepository.save(
            UtilisateurDTO.toEntity(dto)
        )
    );
  }

  private boolean userAlreadyExists(String email) {
    Optional<Utilisateur> user = utilisateurRepository.findUtilisateurByEmail(email);
    log.error("Utilisateur is not valid {}", email);
    return user.isPresent();
  }

  @Override
  public UtilisateurDTO findById(Integer id) {
    if (id == null) {
      log.error("Utilisateur ID is null");
      return null;
    }
    return utilisateurRepository.findById(id)
        .map(UtilisateurDTO::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucun utilisateur avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.UTILISATEUR_NOT_FOUND)
        );
  }

  @Override
  public List<UtilisateurDTO> findAll() {
    return utilisateurRepository.findAll().stream()
        .map(UtilisateurDTO::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Utilisateur ID is null");
      return;
    }
    utilisateurRepository.deleteById(id);
  }

  @Override
  public UtilisateurDTO findByEmail(String email) {
    return utilisateurRepository.findUtilisateurByEmail(email)
        .map(UtilisateurDTO::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
        "Aucun utilisateur avec l'email = " + email + " n' ete trouve dans la BDD",
        ErrorCodes.UTILISATEUR_NOT_FOUND)
    );
  }


}
