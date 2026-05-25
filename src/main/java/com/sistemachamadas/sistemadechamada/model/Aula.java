package com.sistemachamadas.sistemadechamada.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "aulas")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Disciplina é obrigatória")
    private String disciplina;

    @NotNull(message = "Data é obrigatória")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Usuario professor;

    @OneToMany(mappedBy = "aula", cascade = CascadeType.ALL)
    private List<Presenca> presencas;
}