package com.duodev.duodevbackend.controller;

import com.duodev.duodevbackend.dto.RecuperarSenhaDto;
import com.duodev.duodevbackend.model.UsuarioAreaInteresse;
import com.duodev.duodevbackend.model.UsuarioCompetencia;
import com.duodev.duodevbackend.service.UsuarioAreaInteresseService;
import com.duodev.duodevbackend.service.UsuarioCompetenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.duodev.duodevbackend.model.Usuario;
import com.duodev.duodevbackend.repository.UsuarioRepository;
import com.duodev.duodevbackend.service.UsuarioService;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioCompetenciaService usuarioCompetenciaService;

    @Autowired
    private UsuarioAreaInteresseService usuarioAreaInteresseService;

    @PostMapping
    public ResponseEntity<Usuario> adicionarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioCriado = usuarioService.createUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterUsuario(@PathVariable Integer id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = usuarioService.updateUsuario(id, usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public Usuario registerUser(@Valid @RequestBody Usuario usuario) {
        return usuarioService.createUsuario(usuario);
    }

    @GetMapping("/competencia/{idCompetencia}")
    public ResponseEntity<List<UsuarioCompetencia>> getUsuariosByCompetenciaId(
            @PathVariable("idCompetencia") int competenciaId,
            @RequestParam(value = "anosExpMin", required = false) Integer anosExpMin) {
        List<UsuarioCompetencia> usuarios = usuarioCompetenciaService.findUsuariosByCompetenciaId(competenciaId, anosExpMin);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/areaInteresse/{idAreaInteresse}")
    public ResponseEntity<List<UsuarioAreaInteresse>> getUsuariosByAreaInteresseId(
            @PathVariable("idAreaInteresse") int competenciaId) {
        List<UsuarioAreaInteresse> usuarios = usuarioAreaInteresseService.findUsuariosByAreaInteresseId(competenciaId);
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/resetarSenha")
    public ResponseEntity<String> resetarSenha(@RequestBody RecuperarSenhaDto recuperarSenhaDto) {
        usuarioService.resetPassword(recuperarSenhaDto.senha(), recuperarSenhaDto.email());
        return ResponseEntity.ok("Senha resetada com sucesso.");
    }

}
