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
@Table(name="client")
public class Client {

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
    @Column(name ="numtelephone")
    private String numTelephone;
    @Column(name = "idEntreprise")
    private Integer idEntreprise;

    @OneToMany(mappedBy ="client")
    private List<CommandeClient> commandeClient;
}
