package com.duodev.duodevbackend.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class CompetenciaMentoriaId implements Serializable {
    @Column(name = "id_mentoria")
    private int idMentoria;
    
    @Column(name = "id_competencia")
    private int idCompetencia;
}