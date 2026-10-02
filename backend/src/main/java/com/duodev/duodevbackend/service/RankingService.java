package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.model.Usuario;
import com.duodev.duodevbackend.model.Mentoria;
import com.duodev.duodevbackend.repository.UsuarioRepository;
import com.duodev.duodevbackend.repository.MentoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RankingService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MentoriaRepository mentoriaRepository;

    public List<Map.Entry<Usuario, Double>> getRankingMentores() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        Map<Usuario, Double> usuarioPontuacaoMap = new HashMap<>();
        for (Usuario usuario : usuarios) {
            List<Mentoria> mentorias = mentoriaRepository.findByUsuarioMentor(usuario);
            double media = mentorias.stream().mapToDouble(Mentoria::getPontuacaoMentor).average().orElse(0.0);
            usuarioPontuacaoMap.put(usuario, media);
        }

        return usuarioPontuacaoMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Usuario, Double>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    public List<Map.Entry<Usuario, Double>> getRankingMentorados() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        Map<Usuario, Double> usuarioPontuacaoMap = new HashMap<>();
        for (Usuario usuario : usuarios) {
            List<Mentoria> mentorias = mentoriaRepository.findByUsuarioMentorado(usuario);
            double media = mentorias.stream().mapToDouble(Mentoria::getPontuacaoMentorado).average().orElse(0.0);
            usuarioPontuacaoMap.put(usuario, media);
        }

        return usuarioPontuacaoMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Usuario, Double>comparingByValue().reversed())
                .collect(Collectors.toList());
    }
}
