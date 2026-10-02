package com.duodev.duodevbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "mentoria")
@Getter
@Setter
@Schema(name = "Mentoria", description = "Objeto mentoria")
public class Mentoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID da mentoria", example = "1")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_usuario_mentorado")
    @Schema(description = "Usuário mentorado")
    private Usuario usuarioMentorado;

    @ManyToOne
    @JoinColumn(name = "id_usuario_mentor")
    @Schema(description = "Usuário mentor")
    private Usuario usuarioMentor;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Data inicial da mentoria", example = "2024-12-31")
    private LocalDate dataInicial;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Data final da mentoria", example = "2024-12-31")
    private LocalDate dataFinal;

    @Schema(description = "Pontuação do mentor", example = "10")
    private int pontuacaoMentor;

    @Schema(description = "Pontuação do mentorado", example = "8")
    private int pontuacaoMentorado;

}