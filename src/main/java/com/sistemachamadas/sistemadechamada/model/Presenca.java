package com.sistemachamadas.sistemadechamada.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "presencas")
public class Presenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Usuario aluno;

    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;

    @NotNull(message = "Status da presença deve ser informado")
    private Boolean presente;
}