package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.model.Sessao;
import com.duodev.duodevbackend.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessaoService {
    @Autowired
    private SessaoRepository sessaoRepository;

    public Sessao createSessao(Sessao sessao) {
        return sessaoRepository.save(sessao);
    }

    public Sessao updateSessao(Sessao sessao) {
        return sessaoRepository.save(sessao);
    }

    public void deleteSessaoById(Long id) {
        sessaoRepository.deleteById(id);
    }

    public Sessao getSessaoById(Long id) {
        return sessaoRepository.findById(id).orElse(null);
    }

    public Iterable<Sessao> getAllSessao() {
        return sessaoRepository.findAll();
    }



}
