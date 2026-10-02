package com.duodev.duodevbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarioareainteresse")
@Getter
@Setter
public class UsuarioAreaInteresse {
    @EmbeddedId
    private UsuarioAreaInteresseId id;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @MapsId("idCompetencia")
    @JoinColumn(name = "id_competencia")
    private  Competencia competencia;
}