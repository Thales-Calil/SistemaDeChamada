package com.sistema.turma.dto;

import com.sistema.turma.model.Disciplina;
import lombok.Data;

@Data
public class DisciplinaResponseDTO {

    private Long id;
    private String nome;
    private String codigo;

    public DisciplinaResponseDTO(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
        this.codigo = disciplina.getCodigo();
    }
}