
package com.sistema.chamada.controller;

import com.sistema.chamada.dto.PresencaTurmaDTO;
import com.sistema.chamada.service.PresencaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presencas")
@CrossOrigin(origins = "*")
public class PresencaController {

    @Autowired
    private PresencaService presencaService;

    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<List<PresencaTurmaDTO>> calcularPorTurma(
            @PathVariable Long turmaId) {

        return ResponseEntity.ok(
                presencaService.calcularPorTurma(turmaId)
        );
    }
}