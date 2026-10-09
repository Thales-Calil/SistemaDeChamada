package com.sistema.turma.controller;

import com.sistema.turma.dto.TurmaRequestDTO;
import com.sistema.turma.dto.TurmaResponseDTO;
import com.sistema.turma.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
@CrossOrigin(origins = "*")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<TurmaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(
                turmaService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                turmaService.buscarPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<TurmaResponseDTO> cadastrar(
            @Valid @RequestBody TurmaRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(turmaService.cadastrar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody TurmaRequestDTO dto) {

        return ResponseEntity.ok(
                turmaService.atualizar(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        turmaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}