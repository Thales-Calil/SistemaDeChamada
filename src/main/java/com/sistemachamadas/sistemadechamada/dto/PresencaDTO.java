package com.sistemachamadas.sistemadechamada.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PresencaDTO {

    private Long id;

    @NotNull(message = "Aluno deve ser informado")
    private Long alunoId;

    @NotNull(message = "Aula deve ser informada")
    private Long aulaId;

    @NotNull(message = "Presença deve ser informada")
    private Boolean presente;
}