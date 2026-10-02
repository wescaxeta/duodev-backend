package com.duodev.duodevbackend.repository;

import com.duodev.duodevbackend.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EmailRepository extends JpaRepository<Email, Integer> {

    Email findByInviteId(String inviteId);
}
