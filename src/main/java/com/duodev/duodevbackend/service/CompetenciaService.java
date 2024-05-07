package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.model.Competencia;
import com.duodev.duodevbackend.repository.CompetenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetenciaService {

    @Autowired
    private CompetenciaRepository competenciaRepository;

    public Competencia saveCompetencia(Competencia competencia) {
        return competenciaRepository.save(competencia);
    }

    public Competencia updateCompetencia(Competencia competencia) {
        return competenciaRepository.save(competencia);
    }

    public void deleteCompetencia(Long id) {
        competenciaRepository.deleteById(id);
    }

    public Competencia getCompetenciaById(Long id) {
        return competenciaRepository.findById(id).orElse(null);
    }

    public Iterable<Competencia> getAllCompetencia() {
        return competenciaRepository.findAll();
    }

}
