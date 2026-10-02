package com.duodev.duodevbackend.repository;

import com.duodev.duodevbackend.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByToken(String token);
    Token findByEmail(String email);
}
