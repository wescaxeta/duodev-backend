package com.duodev.duodevbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "competencia")
@Getter
@Setter
public class Competencia {
    @Id
    private Long id;

    @NotNull
    private String nome;
}
