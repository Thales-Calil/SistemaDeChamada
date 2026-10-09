package com.sistema.turma.controller;

import com.sistema.turma.dto.ProfessorRequestDTO;
import com.sistema.turma.dto.ProfessorResponseDTO;
import com.sistema.turma.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
@CrossOrigin(origins = "*")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> listarTodos() {

        return ResponseEntity.ok(
                professorService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                professorService.buscarPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> cadastrar(
            @Valid @RequestBody ProfessorRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(professorService.cadastrar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProfessorRequestDTO dto) {

        return ResponseEntity.ok(
                professorService.atualizar(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        professorService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}