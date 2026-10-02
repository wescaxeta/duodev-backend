package com.duodev.duodevbackend.repository;

import com.duodev.duodevbackend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Usuario findByEmail(String email);
}
