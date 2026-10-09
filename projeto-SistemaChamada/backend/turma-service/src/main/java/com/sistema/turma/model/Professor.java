package com.sistema.turma.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "professores")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;
}