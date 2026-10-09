package com.sistema.turma.dto;

import com.sistema.turma.model.Turma;
import lombok.Data;

@Data
public class TurmaResponseDTO {

    private Long id;
    private String nome;
    private Long professorId;
    private String professorNome;
    private Long disciplinaId;
    private String disciplinaNome;

    public TurmaResponseDTO(Turma turma) {
        this.id = turma.getId();
        this.nome = turma.getNome();

        this.professorId = turma.getProfessor().getId();
        this.professorNome = turma.getProfessor().getNome();

        this.disciplinaId = turma.getDisciplina().getId();
        this.disciplinaNome = turma.getDisciplina().getNome();
    }
}