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
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /* we have to mappBy cathegory the list of articles using OneToMany tag */
    @OneToMany(mappedBy="category")
    private List<Article> article;

    @Column(name ="codeCategorie")
    private String codeCategorie;

    @Column(name ="nom")
    private String nom;

    @Column(name ="idEntreprise")
    private Integer idEntreprise;
}
