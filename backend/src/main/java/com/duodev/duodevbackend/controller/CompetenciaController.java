package com.duodev.duodevbackend.controller;

import com.duodev.duodevbackend.model.UsuarioCompetencia;
import com.duodev.duodevbackend.service.UsuarioCompetenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duodev.duodevbackend.model.Competencia;
import com.duodev.duodevbackend.service.CompetenciaService;

import java.util.List;

@RestController
@RequestMapping("/competencia")
public class CompetenciaController {

  @Autowired
  private UsuarioCompetenciaService usuarioCompetenciaService;

  @Autowired
  private CompetenciaService competenciaService;

  @PostMapping
  public ResponseEntity<Competencia> adicionarCompetencia(@RequestBody Competencia competencia) {
    Competencia competenciaCriada = competenciaService.createCompetencia(competencia);
    return ResponseEntity.status(HttpStatus.CREATED).body(competenciaCriada);
  }

  @GetMapping
  public ResponseEntity<List<Competencia>> listarCompetencias() {
    List<Competencia> competencias = competenciaService.getAllCompetencias();
    return ResponseEntity.ok(competencias);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Competencia> obterCompetencia(@PathVariable Integer id) {
    Competencia competencia = competenciaService.getCompetenciaById(id);
    return ResponseEntity.ok(competencia);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Competencia> atualizarCompetencia(@PathVariable Integer id, @RequestBody Competencia competencia) {
    Competencia competenciaAtualizada = competenciaService.updateCompetencia(id, competencia);
    return ResponseEntity.ok(competenciaAtualizada);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarCompetencia(@PathVariable Integer id) {
    competenciaService.deleteCompetencia(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/usuario/{idUsuario}")
  public ResponseEntity<List<UsuarioCompetencia>> getCompetenciaByUsuarioId(@PathVariable("idUsuario") int usuarioId) {
    List<UsuarioCompetencia> usuarios = usuarioCompetenciaService.findCompetenciasByUsuarioId(usuarioId);
    return ResponseEntity.ok(usuarios);
  }
}