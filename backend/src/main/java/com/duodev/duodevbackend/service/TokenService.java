package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.model.Token;
import com.duodev.duodevbackend.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private EmailService emailService; 

    public String createToken(String email) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token(tokenValue, false, email);
        tokenRepository.save(token);
        
        // Send the token via email
        emailService.sendTokenEmail(email, tokenValue); 
        
        return tokenValue.trim();
    }

    public boolean isTokenUsed(String tokenValue) {
        Token token = tokenRepository.findByToken(tokenValue);
        return token != null && token.isUsed();
    }

    public void useToken(String tokenValue) {
        Token token = tokenRepository.findByToken(tokenValue);
        if (token != null) {
            token.setUsed(true);
            tokenRepository.save(token);
        }
    }

    public Token getTokenByEmail(String email) {
        return tokenRepository.findByEmail(email);
    }

    public void sendTokenEmail(String emailAddress, String token) {
        emailService.sendTokenEmail(emailAddress, token);
    }
}
