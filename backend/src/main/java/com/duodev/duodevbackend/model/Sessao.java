package com.duodev.duodevbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import com.duodev.duodevbackend.enums.Status;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "sessao")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_mentoria")
    private Mentoria mentoria;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataHoraInicial;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataHoraFinal;

    @NotNull
    private Status status;

    private String linkMeet;
    private String eventGoogleCalendarId;

    private String invite;

}
