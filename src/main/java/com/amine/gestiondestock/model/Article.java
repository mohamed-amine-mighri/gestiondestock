package com.amine.gestiondestock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name ="codeArticle")
    private String codeArticle;
    @Column(name ="nom")
    private String nom;
    @Column(name ="prix")
    private BigDecimal prix;
    @Column(name ="photo")
    private String photo;

    // we must add the entreprise in here using the joinColumn(mappedBy)//
    @OneToOne
    @JoinColumn(name = "idEntreprise")
    private Entreprise entreprise;



    @ManyToOne
    @JoinColumn(name = "idcategory")
    private Category category;

    @OneToMany(mappedBy = "article")
    private List<LigneCommandeClient> ligneCommandeClients;

    @OneToMany(mappedBy = "article")
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;

    @OneToMany(mappedBy = "article")
    private List<MvtStk> mvtStks;

}
