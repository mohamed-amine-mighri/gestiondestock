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
@Table(name="entreprise")
public class Entreprise extends AbstractEntity{


    @Column(name ="nom")
    private String nom;
    @Column(name ="description")
    private String description;
    @Column(name ="adresse")
    private String adresse;
    @Column(name ="photo")
    private String photo;
    @Column(name ="email")
    private String email;
    @Column(name ="numtelephone")
    private String numTelephone;
    @Column(name ="siteWeb")
    private String siteWeb;

    // we must add the use in here using the joinColumn(mappedBy) entreprise to the List of users//
    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs;

}
