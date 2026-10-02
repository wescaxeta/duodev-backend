package com.duodev.duodevbackend.repository;

import com.duodev.duodevbackend.model.Mentoria;
import com.duodev.duodevbackend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentoriaRepository extends JpaRepository<Mentoria, Integer> {
    List<Mentoria> findByUsuarioMentor(Usuario usuarioMentor);
    List<Mentoria> findByUsuarioMentorado(Usuario usuarioMentorado);
}
