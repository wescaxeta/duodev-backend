package com.duodev.duodevbackend.controller;

import com.duodev.duodevbackend.dto.UsuarioAreaInteresseDto;
import com.duodev.duodevbackend.dto.UsuarioAreaInteresseDto;
import com.duodev.duodevbackend.model.UsuarioAreaInteresse;
import com.duodev.duodevbackend.service.UsuarioAreaInteresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario-area-interesse")
public class UsuarioAreaInteresseController {
    @Autowired
    private UsuarioAreaInteresseService usuarioAreaInteresseService;

    @PostMapping("/single")
    public ResponseEntity<UsuarioAreaInteresse> addAreaInteresseToUsuario(
            @RequestBody UsuarioAreaInteresseDto usuarioAreaInteresseDto) {

        UsuarioAreaInteresse usuarioAreaInteresse = usuarioAreaInteresseService.addAreaInteresseToUsuario(
                usuarioAreaInteresseDto.getUsuarioId(),
                usuarioAreaInteresseDto.getCompetenciaId());

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioAreaInteresse);
    }

    @PostMapping("/multiple")
    public ResponseEntity<List<UsuarioAreaInteresse>> addMultipleAreasInteressesToUsuario(
            @RequestBody List<UsuarioAreaInteresseDto> areaInteressesDto) {

        List<UsuarioAreaInteresse> usuarioAreasInteresses = usuarioAreaInteresseService.addMultipleAreasInteressesToUsuario(areaInteressesDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioAreasInteresses);
    }

    @DeleteMapping("/single")
    public ResponseEntity<Void> removeAreaInteresseFromUsuario(@RequestBody UsuarioAreaInteresseDto usuarioAreaInteresseDto) {
        usuarioAreaInteresseService.removeAreaInteresseFromUsuario(usuarioAreaInteresseDto.getUsuarioId(), usuarioAreaInteresseDto.getCompetenciaId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/multiple")
    public ResponseEntity<Void> removeMultipleAreasInteressesFromUsuario(@RequestBody List<UsuarioAreaInteresseDto> areaInteressesIdDto) {
        usuarioAreaInteresseService.removeMultipleAreasInteressesFromUsuario(areaInteressesIdDto);
        return ResponseEntity.noContent().build();
    }

}
