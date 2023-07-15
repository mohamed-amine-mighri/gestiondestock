package com.amine.gestiondestock.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="Utilisateur")
public class Utilisateur {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name ="nom")
    private String nom;
    @Column(name ="prenom")
    private String prenom;
    @Column(name ="photo")
    private String photo;
    @Column(name ="email")
    private String email;
    @Column(name ="adresse")
    private String adresse;
    @Column(name ="moteDePasse")
    private String moteDePasse;

    @ManyToOne
    @JoinColumn(name ="idEntreprise")
    private Entreprise entreprise;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "utilisateur")
    private List<Roles> role;
}
