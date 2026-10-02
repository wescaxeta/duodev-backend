package com.duodev.duodevbackend.controller;

import com.duodev.duodevbackend.model.Usuario;
import com.duodev.duodevbackend.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/ranking/mentores")
    public List<Map.Entry<Usuario, Double>> getRankingMentores() {
        return rankingService.getRankingMentores();
    }

    @GetMapping("/ranking/mentorados")
    public List<Map.Entry<Usuario, Double>> getRankingMentorados() {
        return rankingService.getRankingMentorados();
    }
}
