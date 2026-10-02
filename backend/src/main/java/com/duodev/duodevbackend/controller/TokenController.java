package com.duodev.duodevbackend.controller;

import com.duodev.duodevbackend.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/generateToken")
    public ResponseEntity<String> generateToken(@RequestParam String email) {
        return ResponseEntity.ok().body(tokenService.createToken(email));
    }

    @GetMapping("/checkToken/{token}")
    public boolean checkToken(@PathVariable String token) {
        return tokenService.isTokenUsed(token);
    }

    @PostMapping("/useToken/{token}")
    public String useToken(@PathVariable String token) {
        if (!tokenService.isTokenUsed(token)) {
            tokenService.useToken(token);
            return "Token validado com sucesso.";
        } else {
            return "Token já utilizado.";
        }
    }

    @PostMapping("/sendTokenEmail")
    public String sendTokenEmail(@RequestParam String email, @RequestParam String token) {
        tokenService.sendTokenEmail(email, token);
        return "Token enviado com sucesso para o email:  " + email;
    }
}
