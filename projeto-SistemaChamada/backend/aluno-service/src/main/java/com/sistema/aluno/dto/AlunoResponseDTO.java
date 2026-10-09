package com.sistema.aluno.dto;

import com.sistema.aluno.model.Aluno;
import lombok.Data;

@Data
public class AlunoResponseDTO {

    private Long id;
    private String nome;
    private String matricula;
    private String email;

    public AlunoResponseDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.matricula = aluno.getMatricula();
        this.email = aluno.getEmail();
    }
}