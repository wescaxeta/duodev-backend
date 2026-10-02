package com.duodev.duodevbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "competenciamentoria")
@Getter
@Setter
public class CompetenciaMentoria {
    @EmbeddedId
    private CompetenciaMentoriaId id;
    
    @ManyToOne
    @MapsId("idMentoria")
    @JoinColumn(name = "id_mentoria")
    private Mentoria mentoria;

    @ManyToOne
    @MapsId("idCompetencia")
    @JoinColumn(name = "id_competencia")
    private Competencia competencia;

}
