package com.sistema.turma.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TurmaRequestDTO {

    @NotBlank(message = "Nome da turma é obrigatório")
    private String nome;

    @NotNull(message = "Professor é obrigatório")
    private Long professorId;

    @NotNull(message = "Disciplina é obrigatória")
    private Long disciplinaId;
}