package com.duodev.duodevbackend.repository;

import com.duodev.duodevbackend.enums.Status;
import com.duodev.duodevbackend.model.Mentoria;
import com.duodev.duodevbackend.model.Sessao;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SessaoRepository extends JpaRepository<Sessao, Integer> {


    List<Sessao> findByDataHoraFinal(LocalDateTime dataHoraFinal);
    List<Sessao> findByDataHoraInicial(LocalDateTime dataHoraInicial);
    List<Sessao> findByDataHoraInicialBetween(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal);

    List<Sessao> findByMentoria(Mentoria mentoria);

    List<Sessao> findByStatus(Status status);

    @Query("SELECT s FROM Sessao s WHERE s.invite = ?1")
    Sessao findByInvite(String inviteId);
}