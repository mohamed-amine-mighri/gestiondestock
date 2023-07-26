package com.amine.gestiondestock.model;

import jakarta.persistence.*;
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


    @Column(name ="date")
    private Instant date;
    @Column(name ="quantite")
    private BigDecimal quantite;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeMouvement type;
    @Column(name ="idEntreprise")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;
}
