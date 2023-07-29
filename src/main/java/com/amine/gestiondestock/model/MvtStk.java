package com.amine.gestiondestock.model;

import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="MvtStk")
public class MvtStk extends AbstractEntity{

    @Column(name = "datemvt")
    private Instant dateMvt;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @Column(name = "typemvt")
    @Enumerated(EnumType.STRING)
    private TypeMouvement typeMvt;

    @Column(name = "sourcemvt")
    @Enumerated(EnumType.STRING)
    private SourceMouvement sourceMvt;

    @Column(name = "identreprise")
    private Integer idEntreprise;
}
