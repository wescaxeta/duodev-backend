package com.duodev.duodevbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class SessaoDto {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataHoraInicial;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataHoraFinal;
    private String emailMentor;
    private String emailMentorado;

    public LocalDateTime getDataHoraInicial() {
        return dataHoraInicial;
    }

    public LocalDateTime getDataHoraFinal() {
        return dataHoraFinal;
    }

    public String getEmailMentor() {
        return emailMentor;
    }

    public String getEmailMentorado() {
        return emailMentorado;
    }
}
