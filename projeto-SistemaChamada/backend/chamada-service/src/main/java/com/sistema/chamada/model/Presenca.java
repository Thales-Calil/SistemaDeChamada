package com.sistema.chamada.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "presencas")
public class Presenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long chamadaId;

    @Column(nullable = false)
    private Long alunoId;

    @Column(nullable = false)
    private Boolean presente;
}