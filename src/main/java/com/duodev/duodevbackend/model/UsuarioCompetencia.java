package com.duodev.duodevbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuario_competencia")
public class UsuarioCompetencia {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_competencia")
    private Competencia competencia;

    @NotNull
    private int anosExperiencia;

}
