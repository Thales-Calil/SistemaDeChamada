package com.sistema.turma.controller;

import com.sistema.turma.dto.DisciplinaRequestDTO;
import com.sistema.turma.dto.DisciplinaResponseDTO;
import com.sistema.turma.service.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
@CrossOrigin(origins = "*")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<DisciplinaResponseDTO>> listarTodos() {

        return ResponseEntity.ok(
                disciplinaService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                disciplinaService.buscarPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<DisciplinaResponseDTO> cadastrar(
            @Valid @RequestBody DisciplinaRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(disciplinaService.cadastrar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody DisciplinaRequestDTO dto) {

        return ResponseEntity.ok(
                disciplinaService.atualizar(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        disciplinaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}