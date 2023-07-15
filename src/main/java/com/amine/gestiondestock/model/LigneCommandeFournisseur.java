package com.amine.gestiondestock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="lignecommanfournisseur")
public class LigneCommandeFournisseur {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name ="idFournisseur")
    private Integer idFournisseur;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    //we have to joinColumn using ManyToOne idCommandeFournisseur to CommandeFournisseur
    @ManyToOne
    @JoinColumn(name = "idcommandefournisseur")
    private CommandeFournisseur commandeFournisseur;


    @Column(name ="quantite")
    private BigDecimal quantite;
    @Column(name ="prix")
    private BigDecimal prix;
    @Column(name ="idEntreprise")
    private Integer idEntreprise;



   }
