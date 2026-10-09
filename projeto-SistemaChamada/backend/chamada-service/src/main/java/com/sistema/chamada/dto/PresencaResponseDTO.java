package com.sistema.chamada.dto;

import com.sistema.chamada.model.Presenca;
import lombok.Data;

@Data
public class PresencaResponseDTO {

    private Long id;
    private Long chamadaId;
    private Long alunoId;
    private Boolean presente;

    public PresencaResponseDTO(Presenca presenca) {
        this.id = presenca.getId();
        this.chamadaId = presenca.getChamadaId();
        this.alunoId = presenca.getAlunoId();
        this.presente = presenca.getPresente();
    }
}