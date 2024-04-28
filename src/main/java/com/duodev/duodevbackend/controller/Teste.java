package com.duodev.duodevbackend.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/teste")
public class Teste {

    @GetMapping("/")
    public ResponseEntity<String> teste() {
       return ResponseEntity.ok("Teste");
    }

}
