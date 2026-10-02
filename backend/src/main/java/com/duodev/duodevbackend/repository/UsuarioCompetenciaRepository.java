package com.duodev.duodevbackend.repository;

import com.duodev.duodevbackend.model.Usuario;
import com.duodev.duodevbackend.model.UsuarioCompetencia;
import com.duodev.duodevbackend.model.UsuarioCompetenciaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioCompetenciaRepository extends JpaRepository<UsuarioCompetencia, UsuarioCompetenciaId> {

    @Query("SELECT uc FROM UsuarioCompetencia uc WHERE uc.competencia.id = :competenciaId AND (:anosExpMin IS NULL OR :anosExpMin = 0 OR uc.anosExperiencia >= :anosExpMin)")
    List<UsuarioCompetencia> findByCompetenciaId(@Param("competenciaId") int competenciaId, @Param("anosExpMin") Integer anosExpMin);

    @Query("SELECT uc FROM UsuarioCompetencia uc WHERE uc.usuario.id = :usuarioId")
    List<UsuarioCompetencia> findByUsuarioId(@Param("usuarioId") int usuarioId);
}
