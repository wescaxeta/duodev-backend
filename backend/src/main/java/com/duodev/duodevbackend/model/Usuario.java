package com.duodev.duodevbackend.model;
import com.duodev.duodevbackend.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nome;

    @NotNull
    @CPF (message = "CPF inválido")

    @Column(unique = true)
    private String cpf;

    @NotNull
    @Email(message = "Email inválido")
    @Column(unique = true)
    private String email;

    @NotNull(message = "A senha deve ser informada")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotNull
    private Sexo sexo;

    @NotNull
    private LocalDate dataNascimento;

}
