package com.duodev.duodevbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duodev.duodevbackend.model.Mentoria;
import com.duodev.duodevbackend.service.MentoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/mentoria")
@Tag(name = "Mentoria", description = "Endpoints para gestão de mentorias")
public class MentoriaController {

    @Autowired
    private MentoriaService mentoriaService;

    @PostMapping
    @Operation(summary = "Criar uma nova mentoria")
    public ResponseEntity<Mentoria> createMentoria(@RequestBody Mentoria mentoria) {
        Mentoria mentoriaCriada = mentoriaService.createMentoria(mentoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(mentoriaCriada);
    }

    @GetMapping
    @Operation(summary = "Buscar todas as mentorias")
    public ResponseEntity<List<Mentoria>> findAllMentorias() {
        List<Mentoria> mentorias = mentoriaService.getAllMentorias();
        return ResponseEntity.ok(mentorias);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar uma mentoria pelo ID")
    public ResponseEntity<Mentoria> getMentoriaById(@PathVariable Integer id) {
        Mentoria mentoria = mentoriaService.getMentoriaById(id);
        return ResponseEntity.ok(mentoria);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma mentoria pelo ID")
    public ResponseEntity<Mentoria> updateMentoria(@PathVariable Integer id, @RequestBody Mentoria mentoria) {
        Mentoria mentoriaAtualizada = mentoriaService.updateMentoria(id, mentoria);
        return ResponseEntity.ok(mentoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma mentoria pelo ID")
    public ResponseEntity<Void> deleteMentoria(@PathVariable Integer id) {
        mentoriaService.deleteMentoria(id);
        return ResponseEntity.noContent().build();
    }
}
