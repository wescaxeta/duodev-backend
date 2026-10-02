package com.duodev.duodevbackend.controller;

import com.duodev.duodevbackend.model.Email;
import com.duodev.duodevbackend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody Email email) {
        return emailService.sendEmail(email);

    }

}
