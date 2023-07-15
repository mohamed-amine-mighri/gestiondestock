package com.amine.gestiondestock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="role")
public class Roles {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name="roleName")
    private String roleName;


    @ManyToOne
    @JoinColumn(name="idUtilisateur")
    private Utilisateur utilisateur;

}
