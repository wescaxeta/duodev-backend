package com.duodev.duodevbackend.model;


import jakarta.persistence.*;

@Entity
@Table(name = "reuniao")
public class Reuniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
