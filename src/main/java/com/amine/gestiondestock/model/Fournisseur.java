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
@Table(name="fournisseur")
public class Fournisseur extends AbstractEntity{

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
    @Column(name ="numtelephone")
    private String numTelephone;
    @Column(name ="idEntreprise")
    private Integer idEntreprise;

    //we need to join the fournisseur withe the cammandeFournisseur using oneToMany and mappedBy//

    @OneToMany(mappedBy = "fournisseur")
    private List<CommandeFournisseur> commandeFournisseurs;
}
