package com.sistema.turma.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DisciplinaRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Código é obrigatório")
    private String codigo;
}