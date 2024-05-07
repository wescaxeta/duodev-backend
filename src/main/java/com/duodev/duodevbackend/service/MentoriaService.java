package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.model.Mentoria;
import com.duodev.duodevbackend.repository.MentoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MentoriaService {

    @Autowired
    private MentoriaRepository mentoriaRepository;

    public Mentoria saveMentoria(Mentoria mentoria) {
        return mentoriaRepository.save(mentoria);
    }

    public Mentoria updateMentoria(Mentoria mentoria) {
        return mentoriaRepository.save(mentoria);
    }

    public void deleteMentoria(Long id) {
        mentoriaRepository.deleteById(id);
    }

    public Mentoria getMentoriaById(Long id) {
        return mentoriaRepository.findById(id).orElse(null);
    }

    public Iterable<Mentoria> getAllMentoria() {
        return mentoriaRepository.findAll();
    }

}
