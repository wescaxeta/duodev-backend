package com.duodev.duodevbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "mentoria")
@Getter
@Setter
public class Mentoria {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_usuario_mentorado")
    private Usuario usuarioMentorado;

    @OneToOne
    @JoinColumn(name = "id_usuario_mentor")
    private Usuario usuarioMentor;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicial;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFinal;

    @NotNull
    private int pontuacaoMentor;

    @NotNull
    private int pontuacaoMentorado;

}
