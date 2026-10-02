package com.duodev.duodevbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuariocompetencia")

public class UsuarioCompetencia {
    @EmbeddedId
    private UsuarioCompetenciaId id;
    
    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @MapsId("idCompetencia")
    @JoinColumn(name = "id_competencia")
    private Competencia competencia;

    @NotNull
    private int anosExperiencia;

}
