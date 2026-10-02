package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.model.Competencia;
import com.duodev.duodevbackend.repository.CompetenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenciaService {

    @Autowired
    private CompetenciaRepository competenciaRepository;

    public CompetenciaService(CompetenciaRepository competenciaRepository) {
        this.competenciaRepository = competenciaRepository;
    }

    public Competencia updateCompetencia(int id, Competencia competenciaDetails) {
        Competencia competencia = getCompetenciaById(id);
        competencia.setNome(competenciaDetails.getNome());
        return competenciaRepository.save(competencia);
    }

    public void deleteCompetencia(int id) {
        competenciaRepository.deleteById(id);
    }

    public Competencia getCompetenciaById(int id) {
        return competenciaRepository.findById(id).orElse(null);
    }

    public Competencia createCompetencia(Competencia competencia) {
        return competenciaRepository.save(competencia);
    }

    public List<Competencia> getAllCompetencias() {
            return competenciaRepository.findAll();
    }
}
