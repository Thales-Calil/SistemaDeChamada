package com.sistema.chamada.controller;

import com.sistema.chamada.dto.ChamadaResponseDTO;
import com.sistema.chamada.dto.CriarChamadaDTO;
import com.sistema.chamada.dto.PresencaResponseDTO;
import com.sistema.chamada.dto.RegistrarPresencaDTO;
import com.sistema.chamada.service.ChamadaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chamadas")
@CrossOrigin(origins = "*")
public class ChamadaController {

    @Autowired
    private ChamadaService chamadaService;

    @PostMapping
    public ResponseEntity<ChamadaResponseDTO> criar(
            @Valid @RequestBody CriarChamadaDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(chamadaService.criar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                chamadaService.buscarPorId(id)
        );
    }

    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<List<ChamadaResponseDTO>> listarPorTurma(
            @PathVariable Long turmaId) {

        return ResponseEntity.ok(
                chamadaService.listarPorTurma(turmaId)
        );
    }

    @PostMapping("/{id}/presencas")
    public ResponseEntity<PresencaResponseDTO> registrarPresenca(
            @PathVariable Long id,
            @Valid @RequestBody RegistrarPresencaDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        chamadaService.registrarPresenca(id, dto)
                );
    }

    @PutMapping("/{id}/presencas/{alunoId}")
    public ResponseEntity<PresencaResponseDTO> atualizarPresenca(
            @PathVariable Long id,
            @PathVariable Long alunoId,
            @Valid @RequestBody RegistrarPresencaDTO dto) {

        return ResponseEntity.ok(
                chamadaService.atualizarPresenca(
                        id,
                        alunoId,
                        dto
                )
        );
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Void> finalizar(
            @PathVariable Long id) {

        chamadaService.finalizar(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        chamadaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}