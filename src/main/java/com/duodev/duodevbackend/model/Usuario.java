package com.duodev.duodevbackend.model;

import com.duodev.duodevbackend.enums.Sexo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    private String nome;

    @NotNull
    @CPF
    @Column(unique = true)
    private String cpf;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String senha;

    @NotNull
    private Enum<Sexo> sexo;


}
