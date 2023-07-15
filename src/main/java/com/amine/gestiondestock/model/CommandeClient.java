package com.amine.gestiondestock.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="commandeClient")
public class CommandeClient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name ="codeCommande")
    private String codeCommande;
    @Column(name ="date")
    private Instant date;
    @Column(name = "etatcommande")
    @Enumerated(EnumType.STRING)
    private EtatCommande etatCommande;

    @Column(name ="idEntreprise")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name ="idClient")
    private Client client;

    @OneToMany(mappedBy="commandeClient")
    private List<LigneCommandeClient> ligneCommandeClient;

}
