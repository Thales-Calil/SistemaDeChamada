package com.sistemachamadas.sistemadechamada.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AulaDTO {

    private Long id;

    @NotBlank(message = "Disciplina é obrigatória")
    @Size(min = 3, message = "Disciplina deve ter no mínimo 3 caracteres")
    private String disciplina;

    @NotNull(message = "Data é obrigatória")
    private LocalDate data;

    @NotNull(message = "Professor deve ser informado")
    private Long professorId;
}