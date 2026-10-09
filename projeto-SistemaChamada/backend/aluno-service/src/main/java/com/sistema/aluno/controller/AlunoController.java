package com.sistema.aluno.controller;

import com.sistema.aluno.dto.AlunoRequestDTO;
import com.sistema.aluno.dto.AlunoResponseDTO;
import com.sistema.aluno.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
@CrossOrigin(origins = "*")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listarTodos() {

        return ResponseEntity.ok(
                alunoService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(
                alunoService.buscarPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> cadastrar(@Valid @RequestBody AlunoRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(alunoService.cadastrar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AlunoRequestDTO dto) {

        return ResponseEntity.ok(
                alunoService.atualizar(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        alunoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}