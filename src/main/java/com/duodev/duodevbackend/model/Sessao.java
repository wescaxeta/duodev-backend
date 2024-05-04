package com.duodev.duodevbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Not;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "sessao")
public class Sessao {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_mentoria")
    private Mentoria mentoria;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHoraInicial;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHoraFinal;

    @NotNull
    private Byte status;

}
