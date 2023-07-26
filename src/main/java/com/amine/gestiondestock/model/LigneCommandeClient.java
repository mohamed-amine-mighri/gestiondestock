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
@Table(name="lignedecommandeclient")
public class LigneCommandeClient extends AbstractEntity{

    @Column(name ="idClient")
    private Integer idClient;

    @Column(name ="quantite")
    private BigDecimal quantite;

    @Column(name ="prix")
    private BigDecimal prix;

    @Column(name ="idEntreprise")
    private Integer idEntreprise;


    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "idcommandeclient")
    private CommandeClient commandeClient;

}
