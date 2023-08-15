package com.amine.gestiondestock.services.servicImplimentation;

import com.amine.gestiondestock.DTO.ChangerMotDePasseUtilisateurDto;
import com.amine.gestiondestock.DTO.RoleDTO;
import com.amine.gestiondestock.DTO.UtilisateurDTO;
import com.amine.gestiondestock.Exception.EntityNotFoundException;
import com.amine.gestiondestock.Exception.ErrorCodes;
import com.amine.gestiondestock.Exception.InvalidEntityException;
import com.amine.gestiondestock.Exception.InvalidOperationException;
import com.amine.gestiondestock.model.Roles;
import com.amine.gestiondestock.model.Utilisateur;
import com.amine.gestiondestock.repos.RolesRepository;
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
import org.springframework.util.StringUtils;


@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

  private UtilisateurRepos utilisateurRepository;
  private final PasswordEncoder passwordEncoder;
  @Autowired
  private RolesRepository rolesRepository;

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
      UtilisateurServiceImpl.log.error("Utilisateur is not valid {}", dto);
      //throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
    }

    if(userAlreadyExists(dto.getEmail())) {
      UtilisateurServiceImpl.log.error("Utilisateur is not valid {}", dto);
      throw new InvalidEntityException("Un autre utilisateur avec le meme email existe deja", ErrorCodes.UTILISATEUR_ALREADY_EXISTS,
          Collections.singletonList("Un autre utilisateur avec le meme email existe deja dans la BDD"));
    }

    dto.setMoteDePasse(passwordEncoder.encode(dto.getMoteDePasse()));

    UtilisateurDTO savedUser = UtilisateurDTO.fromEntity(
            utilisateurRepository.save(
                    UtilisateurDTO.toEntity(dto)
            )
    );

//    // Create the RoleDTO object for "ADMIN" role and associate it with the saved user
//    RoleDTO adminRoleDto = RoleDTO.builder()
//            .roleName("ADMIN")
//            .utilisateur(savedUser)
//            .build();
//
//    // Save the "ADMIN" role associated with the user
//    System.out.println("this is what u looking for ===>"+adminRoleDto);
//    System.out.println("this is what u looking for ===>"+rolesRepository);
//    RoleDTO.fromEntity((Roles) rolesRepository.save(RoleDTO.toEntity(adminRoleDto)));
//    System.out.println("this is what u looking for ===>"+savedUser);
    // Return the saved user
    return savedUser;

  }

  private boolean userAlreadyExists(String email) {
    Optional<Utilisateur> user = utilisateurRepository.findUtilisateurByEmail(email);
    UtilisateurServiceImpl.log.error("Utilisateur is not valid {}", email);
    return user.isPresent();
  }

  @Override
  public UtilisateurDTO findById(Integer id) {
    if (id == null) {
      UtilisateurServiceImpl.log.error("Utilisateur ID is null");
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
      UtilisateurServiceImpl.log.error("Utilisateur ID is null");
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


  @Override
  public UtilisateurDTO changerMotDePasse(ChangerMotDePasseUtilisateurDto dto) {
    validate(dto);
    Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(dto.getId());
    if (utilisateurOptional.isEmpty()) {
      log.warn("Aucun utilisateur n'a ete trouve avec l'ID " + dto.getId());
      throw new EntityNotFoundException("Aucun utilisateur n'a ete trouve avec l'ID " + dto.getId(), ErrorCodes.UTILISATEUR_NOT_FOUND);
    }

    Utilisateur utilisateur = utilisateurOptional.get();
    utilisateur.setMoteDePasse(passwordEncoder.encode(dto.getMotDePasse()));

    return UtilisateurDTO.fromEntity(
            utilisateurRepository.save(utilisateur)
    );
  }

  private void validate(ChangerMotDePasseUtilisateurDto dto) {
    if (dto == null) {
      log.warn("Impossible de modifier le mot de passe avec un objet NULL");
      throw new InvalidOperationException("Aucune information n'a ete fourni pour pouvoir changer le mot de passe",
              ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
    }
    if (dto.getId() == null) {
      log.warn("Impossible de modifier le mot de passe avec un ID NULL");
      throw new InvalidOperationException("ID utilisateur null:: Impossible de modifier le mote de passe",
              ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
    }
    if (!StringUtils.hasLength(dto.getMotDePasse()) || !StringUtils.hasLength(dto.getConfirmMotDePasse())) {
      log.warn("Impossible de modifier le mot de passe avec un mot de passe NULL");
      throw new InvalidOperationException("Mot de passe utilisateur null:: Impossible de modifier le mote de passe",
              ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
    }
    if (!dto.getMotDePasse().equals(dto.getConfirmMotDePasse())) {
      log.warn("Impossible de modifier le mot de passe avec deux mots de passe different");
      throw new InvalidOperationException("Mots de passe utilisateur non conformes:: Impossible de modifier le mote de passe",
              ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
    }
  }


}
