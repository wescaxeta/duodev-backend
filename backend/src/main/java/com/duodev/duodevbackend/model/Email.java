package com.duodev.duodevbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String sendTo;

    @NotNull
    private String sendFrom;

    @NotNull
    private String requestedBy;

    @NotNull
    private String subject;

    @NotNull
    private String body;

    @NotNull
    private LocalDateTime sendDate;

    @OneToOne
    private Usuario usuario;

    @OneToOne
    private Sessao sessao;

    private String inviteId;


}
