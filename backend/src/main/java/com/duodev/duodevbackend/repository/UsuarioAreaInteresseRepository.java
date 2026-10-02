package com.duodev.duodevbackend.repository;

import com.duodev.duodevbackend.model.UsuarioAreaInteresse;
import com.duodev.duodevbackend.model.UsuarioAreaInteresseId;
import com.duodev.duodevbackend.model.UsuarioAreaInteresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioAreaInteresseRepository extends JpaRepository<UsuarioAreaInteresse, UsuarioAreaInteresseId> {
    @Query("SELECT uc FROM UsuarioAreaInteresse uc WHERE uc.competencia.id = :competenciaId")
    List<UsuarioAreaInteresse> findByCompetenciaId(@Param("competenciaId") int competenciaId);

    @Query("SELECT uc FROM UsuarioAreaInteresse uc WHERE uc.usuario.id = :usuarioId")
    List<UsuarioAreaInteresse> findByUsuarioId(@Param("usuarioId") int usuarioId);
}
