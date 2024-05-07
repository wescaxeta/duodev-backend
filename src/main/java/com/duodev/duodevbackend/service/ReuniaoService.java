package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.model.Reuniao;
import com.duodev.duodevbackend.repository.ReuniaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReuniaoService {

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    public Reuniao saveReuniao(Reuniao reuniao) {
        return reuniaoRepository.save(reuniao);
    }

    public Reuniao updateReuniao(Reuniao reuniao) {
        return reuniaoRepository.save(reuniao);
    }

    public void deleteReuniao(Long id) {
        reuniaoRepository.deleteById(id);
    }

    public Reuniao getReuniaoById(Long id) {
        return reuniaoRepository.findById(id).orElse(null);
    }

    public Iterable<Reuniao> getAllReuniao() {
        return reuniaoRepository.findAll();
    }

}
