
package com.sistema.chamada.dto;

import lombok.Data;

@Data
public class PresencaTurmaDTO {

    private Long alunoId;
    private long totalChamadas;
    private long presencas;
    private double percentual;

    public PresencaTurmaDTO(
            Long alunoId,
            long totalChamadas,
            long presencas) {

        this.alunoId = alunoId;
        this.totalChamadas = totalChamadas;
        this.presencas = presencas;

        this.percentual = totalChamadas == 0
                ? 0.0
                : (presencas * 100.0) / totalChamadas;
    }
}