package com.duodev.duodevbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "competencia_mentoria")
@Getter
@Setter
public class CompetenciaMentoria {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_mentoria")
    private Mentoria mentoria;

    @ManyToOne
    @JoinColumn(name = "id_competencia")
    private Competencia competencia;

}
