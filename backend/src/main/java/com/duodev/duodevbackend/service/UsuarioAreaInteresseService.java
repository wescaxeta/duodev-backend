package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.dto.UsuarioAreaInteresseDto;
import com.duodev.duodevbackend.exceptions.ResourceNotFoundException;
import com.duodev.duodevbackend.model.*;
import com.duodev.duodevbackend.repository.CompetenciaRepository;
import com.duodev.duodevbackend.repository.UsuarioAreaInteresseRepository;
import com.duodev.duodevbackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duodev.duodevbackend.repository.UsuarioAreaInteresseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioAreaInteresseService {



    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Autowired
    private UsuarioAreaInteresseRepository usuarioAreaInteresseRepository;

    @Transactional
    public UsuarioAreaInteresse addAreaInteresseToUsuario(int usuarioId, int competenciaId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));
        Competencia competencia = competenciaRepository.findById(competenciaId)
                .orElseThrow(() -> new ResourceNotFoundException("Competencia not found"));

        UsuarioAreaInteresse usuarioAreaInteresse = new UsuarioAreaInteresse();
        usuarioAreaInteresse.setUsuario(usuario);
        usuarioAreaInteresse.setCompetencia(competencia);
        usuarioAreaInteresse.setId(new UsuarioAreaInteresseId(usuario.getId(), competencia.getId()));

        return usuarioAreaInteresseRepository.save(usuarioAreaInteresse);
    }

    @Transactional
    public List<UsuarioAreaInteresse> addMultipleAreasInteressesToUsuario(List<UsuarioAreaInteresseDto> competenciasDTO) {
        List<UsuarioAreaInteresse> usuarioAreaInteresses = competenciasDTO.stream().map(dto -> {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));
            Competencia competencia = competenciaRepository.findById(dto.getCompetenciaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Competencia not found"));

            UsuarioAreaInteresse usuarioAreaInteresse = new UsuarioAreaInteresse();
            usuarioAreaInteresse.setUsuario(usuario);
            usuarioAreaInteresse.setCompetencia(competencia);
            usuarioAreaInteresse.setId(new UsuarioAreaInteresseId(usuario.getId(), competencia.getId()));

            return usuarioAreaInteresse;
        }).collect(Collectors.toList());

        return usuarioAreaInteresseRepository.saveAll(usuarioAreaInteresses);
    }

    @Transactional
    public void removeAreaInteresseFromUsuario(int usuarioId, int competenciaId) {
        UsuarioAreaInteresseId usuarioAreaInteresseId = new UsuarioAreaInteresseId(usuarioId, competenciaId);
        usuarioAreaInteresseRepository.deleteById(usuarioAreaInteresseId);
    }

    @Transactional
    public void removeMultipleAreasInteressesFromUsuario(List<UsuarioAreaInteresseDto> competenciasIdDto) {
        for (UsuarioAreaInteresseDto idDto : competenciasIdDto) {
            UsuarioAreaInteresseId usuarioAreaInteresseId = new UsuarioAreaInteresseId(
                    idDto.getUsuarioId(),
                    idDto.getCompetenciaId());
            usuarioAreaInteresseRepository.deleteById(usuarioAreaInteresseId);
        }
    }

    public List<UsuarioAreaInteresse> findUsuariosByAreaInteresseId(int competenciaId) {
        return usuarioAreaInteresseRepository.findByCompetenciaId(competenciaId);
    }

    public List<UsuarioAreaInteresse> findAreasInteressesByUsuarioId(int usuarioId) {
        return usuarioAreaInteresseRepository.findByUsuarioId(usuarioId);
    }

}
