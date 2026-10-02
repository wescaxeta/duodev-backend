package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.model.CompetenciaMentoria;
import com.duodev.duodevbackend.repository.CompetenciaMentoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetenciaMentoriaService {

    @Autowired
    private CompetenciaMentoriaRepository competenciaMentoriaRepository;

    public CompetenciaMentoria saveCompetenciaMentoria(CompetenciaMentoria competenciaMentoria) {
        return competenciaMentoriaRepository.save(competenciaMentoria);
    }

    public CompetenciaMentoria updateCompetenciaMentoria(CompetenciaMentoria competenciaMentoria) {
        return competenciaMentoriaRepository.save(competenciaMentoria);
    }

    public void deleteCompetenciaMentoria(int id) {
        competenciaMentoriaRepository.deleteById(id);
    }

    public CompetenciaMentoria getCompetenciaMentoriaById(int id) {
        return competenciaMentoriaRepository.findById(id).orElse(null);
    }

    public Iterable<CompetenciaMentoria> getAllCompetenciaMentoria() {
        return competenciaMentoriaRepository.findAll();
    }


}
