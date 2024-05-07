package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.model.UsuarioCompetencia;
import com.duodev.duodevbackend.repository.UsuarioCompetenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioCompetenciaService {

    @Autowired
    private UsuarioCompetenciaRepository usuarioCompetenciaRepository;

    public UsuarioCompetencia createUsuarioCompetencia(UsuarioCompetencia usuarioCompetencia) {
        return usuarioCompetenciaRepository.save(usuarioCompetencia);
    }

    public UsuarioCompetencia updateUsuarioCompetencia(UsuarioCompetencia usuarioCompetencia) {
        return usuarioCompetenciaRepository.save(usuarioCompetencia);
    }

    public void deleteUsuarioCompetenciaById(Long id) {
        usuarioCompetenciaRepository.deleteById(id);
    }

    public UsuarioCompetencia getUsuarioCompetenciaById(Long id) {
        return usuarioCompetenciaRepository.findById(id).orElse(null);
    }

    public Iterable<UsuarioCompetencia> getAllUsuarioCompetencia() {
        return usuarioCompetenciaRepository.findAll();
    }
}
