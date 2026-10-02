package com.duodev.duodevbackend.controller;

import com.duodev.duodevbackend.model.UsuarioCompetencia;
import com.duodev.duodevbackend.service.UsuarioCompetenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.duodev.duodevbackend.dto.UsuarioCompetenciaDto;
import com.duodev.duodevbackend.dto.UsuarioCompetenciaIdDto;

import java.util.List;

@RestController
@RequestMapping("/usuario-competencia")
public class UsuarioCompetenciaController {

    @Autowired
    private UsuarioCompetenciaService usuarioCompetenciaService;

    @PostMapping("/single")
    public ResponseEntity<UsuarioCompetencia> addCompetenciaToUsuario(
            @RequestBody UsuarioCompetenciaDto usuarioCompetenciaDto) {

        UsuarioCompetencia usuarioCompetencia = usuarioCompetenciaService.addCompetenciaToUsuario(
                usuarioCompetenciaDto.getUsuarioId(),
                usuarioCompetenciaDto.getCompetenciaId(),
                usuarioCompetenciaDto.getAnosExperiencia());

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCompetencia);
    }

    @PostMapping("/multiple")
    public ResponseEntity<List<UsuarioCompetencia>> addMultipleCompetenciasToUsuario(
            @RequestBody List<UsuarioCompetenciaDto> competenciasDto) {

        List<UsuarioCompetencia> usuarioCompetencias = usuarioCompetenciaService.addMultipleCompetenciasToUsuario(competenciasDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCompetencias);
    }

    @DeleteMapping("/single")
    public ResponseEntity<Void> removeCompetenciaFromUsuario(@RequestBody UsuarioCompetenciaIdDto usuarioCompetenciaIdDto) {
        usuarioCompetenciaService.removeCompetenciaFromUsuario(usuarioCompetenciaIdDto.getUsuarioId(), usuarioCompetenciaIdDto.getCompetenciaId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/multiple")
    public ResponseEntity<Void> removeMultipleCompetenciasFromUsuario(@RequestBody List<UsuarioCompetenciaIdDto> competenciasIdDto) {
        usuarioCompetenciaService.removeMultipleCompetenciasFromUsuario(competenciasIdDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/multiple")
    public ResponseEntity<List<UsuarioCompetencia>> updateAnosExperienciaMultiple(
            @RequestBody List<UsuarioCompetenciaDto> competenciasDto) {

        List<UsuarioCompetencia> updatedCompetencias = usuarioCompetenciaService.updateAnosExperienciaMultiple(competenciasDto);
        return ResponseEntity.ok(updatedCompetencias);
    }

    @PutMapping("/single")
    public ResponseEntity<UsuarioCompetencia> updateAnosExperiencia(
            @RequestBody UsuarioCompetenciaDto competenciaDto) {
        UsuarioCompetencia updatedCompetencia = usuarioCompetenciaService.updateAnosExperiencia(competenciaDto);
        return ResponseEntity.ok(updatedCompetencia);
    }
}
