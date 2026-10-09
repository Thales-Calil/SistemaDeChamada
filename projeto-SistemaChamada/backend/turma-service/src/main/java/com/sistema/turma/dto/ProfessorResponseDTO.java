package com.sistema.turma.dto;

import com.sistema.turma.model.Professor;
import lombok.Data;

@Data
public class ProfessorResponseDTO {

    private Long id;
    private String nome;
    private String email;

    public ProfessorResponseDTO(Professor professor) {
        this.id = professor.getId();
        this.nome = professor.getNome();
        this.email = professor.getEmail();
    }
}