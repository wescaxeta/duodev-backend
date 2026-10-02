package com.duodev.duodevbackend.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class UsuarioCompetenciaId implements Serializable {
    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "id_competencia")
    private int idCompetencia;

    public UsuarioCompetenciaId(int idUsuario, int idCompetencia) {
        this.idUsuario = idUsuario;
        this.idCompetencia = idCompetencia;
    }

    public UsuarioCompetenciaId() {
    }
}