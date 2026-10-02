package com.duodev.duodevbackend.controller;

import com.duodev.duodevbackend.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.duodev.duodevbackend.service.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/login")
    public ResponseEntity<Object> loginSubmit(@RequestBody Usuario usuarioLogin) {
        Usuario login = usuarioService.realizarLogin(usuarioLogin);
        if (login != null) {
            return ResponseEntity.ok().body(login);
        } else {
            // return json with error message
            return ResponseEntity.badRequest().body("Usuário ou senha inválidos");
        }


    }
}
