package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.exceptions.ResourceNotFoundException;
import com.duodev.duodevbackend.model.Mentoria;
import com.duodev.duodevbackend.model.Usuario;
import com.duodev.duodevbackend.repository.MentoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentoriaService {

    @Autowired
    private MentoriaRepository mentoriaRepository;


    public Mentoria updateMentoria(Integer id, Mentoria mentoriaDetails) {
        Mentoria mentoria = getMentoriaById(id);
        mentoria.setDataInicial(mentoriaDetails.getDataInicial());
        mentoria.setDataFinal(mentoriaDetails.getDataFinal());
        mentoria.setPontuacaoMentor(mentoriaDetails.getPontuacaoMentor());
        mentoria.setPontuacaoMentorado(mentoriaDetails.getPontuacaoMentorado());
        return mentoriaRepository.save(mentoria);
    }

    public void deleteMentoria(int id) {
        mentoriaRepository.deleteById(id);
    }

    public Mentoria getMentoriaById(int id) {
        return mentoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mentoria not found"));
    }

    public Mentoria createMentoria(Mentoria mentoria) {
        return mentoriaRepository.save(mentoria);
    }

    public List<Mentoria> getAllMentorias() {
        return mentoriaRepository.findAll();
    }
}
