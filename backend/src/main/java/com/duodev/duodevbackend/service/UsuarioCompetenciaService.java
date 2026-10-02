package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.exceptions.ResourceNotFoundException;
import com.duodev.duodevbackend.model.Competencia;
import com.duodev.duodevbackend.model.Usuario;
import com.duodev.duodevbackend.model.UsuarioCompetencia;
import com.duodev.duodevbackend.model.UsuarioCompetenciaId;
import com.duodev.duodevbackend.repository.CompetenciaRepository;
import com.duodev.duodevbackend.repository.UsuarioRepository;
import com.duodev.duodevbackend.repository.UsuarioCompetenciaRepository;
import com.duodev.duodevbackend.dto.UsuarioCompetenciaDto;
import com.duodev.duodevbackend.dto.UsuarioCompetenciaIdDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioCompetenciaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Autowired
    private UsuarioCompetenciaRepository usuarioCompetenciaRepository;

    @Transactional
    public UsuarioCompetencia addCompetenciaToUsuario(int usuarioId, int competenciaId, int anosExperiencia) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));
        Competencia competencia = competenciaRepository.findById(competenciaId)
                .orElseThrow(() -> new ResourceNotFoundException("Competencia not found"));

        UsuarioCompetencia usuarioCompetencia = new UsuarioCompetencia();
        usuarioCompetencia.setUsuario(usuario);
        usuarioCompetencia.setCompetencia(competencia);
        usuarioCompetencia.setAnosExperiencia(anosExperiencia);
        usuarioCompetencia.setId(new UsuarioCompetenciaId(usuario.getId(), competencia.getId()));

        return usuarioCompetenciaRepository.save(usuarioCompetencia);
    }

    @Transactional
    public List<UsuarioCompetencia> addMultipleCompetenciasToUsuario(List<UsuarioCompetenciaDto> competenciasDTO) {
        List<UsuarioCompetencia> usuarioCompetencias = competenciasDTO.stream().map(dto -> {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));
            Competencia competencia = competenciaRepository.findById(dto.getCompetenciaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Competencia not found"));

            UsuarioCompetencia usuarioCompetencia = new UsuarioCompetencia();
            usuarioCompetencia.setUsuario(usuario);
            usuarioCompetencia.setCompetencia(competencia);
            usuarioCompetencia.setAnosExperiencia(dto.getAnosExperiencia());
            usuarioCompetencia.setId(new UsuarioCompetenciaId(usuario.getId(), competencia.getId()));

            return usuarioCompetencia;
        }).collect(Collectors.toList());

        return usuarioCompetenciaRepository.saveAll(usuarioCompetencias);
    }

    @Transactional
    public void removeCompetenciaFromUsuario(int usuarioId, int competenciaId) {
        UsuarioCompetenciaId usuarioCompetenciaId = new UsuarioCompetenciaId(usuarioId, competenciaId);
        usuarioCompetenciaRepository.deleteById(usuarioCompetenciaId);
    }

    @Transactional
    public void removeMultipleCompetenciasFromUsuario(List<UsuarioCompetenciaIdDto> competenciasIdDto) {
        for (UsuarioCompetenciaIdDto idDto : competenciasIdDto) {
            UsuarioCompetenciaId usuarioCompetenciaId = new UsuarioCompetenciaId(
                    idDto.getUsuarioId(),
                    idDto.getCompetenciaId());
            usuarioCompetenciaRepository.deleteById(usuarioCompetenciaId);
        }
    }

    @Transactional
    public List<UsuarioCompetencia> updateAnosExperienciaMultiple(List<UsuarioCompetenciaDto> competenciasDTO) {
        List<UsuarioCompetencia> usuarioCompetencias = competenciasDTO.stream().map(dto -> {
            UsuarioCompetenciaId id = new UsuarioCompetenciaId(dto.getUsuarioId(), dto.getCompetenciaId());
            UsuarioCompetencia usuarioCompetencia = usuarioCompetenciaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("UsuarioCompetencia not found"));

            usuarioCompetencia.setAnosExperiencia(dto.getAnosExperiencia());

            return usuarioCompetencia;
        }).collect(Collectors.toList());

        return usuarioCompetenciaRepository.saveAll(usuarioCompetencias);
    }

    public UsuarioCompetencia updateAnosExperiencia(UsuarioCompetenciaDto competenciaDto) {
        UsuarioCompetenciaId id = new UsuarioCompetenciaId(competenciaDto.getUsuarioId(), competenciaDto.getCompetenciaId());
        UsuarioCompetencia usuarioCompetencia = usuarioCompetenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UsuarioCompetencia not found"));

        usuarioCompetencia.setAnosExperiencia(competenciaDto.getAnosExperiencia());
        return usuarioCompetenciaRepository.save(usuarioCompetencia);
    }

    public List<UsuarioCompetencia> findUsuariosByCompetenciaId(int competenciaId, Integer anosExpMin) {
        return usuarioCompetenciaRepository.findByCompetenciaId(competenciaId, anosExpMin);
    }

    public List<UsuarioCompetencia> findCompetenciasByUsuarioId(int usuarioId) {
        return usuarioCompetenciaRepository.findByUsuarioId(usuarioId);
    }
}
