package com.sistemachamadas.sistemadechamada.controller;

import com.sistemachamadas.sistemadechamada.dto.AulaDTO;
import com.sistemachamadas.sistemadechamada.model.Aula;
import com.sistemachamadas.sistemadechamada.service.ChamadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chamada")
@CrossOrigin(origins = "http://localhost:4200")
public class ChamadaController {

    @Autowired
    private ChamadaService chamadaService;

    @GetMapping("/{id}")
    public List<Aula> listarChamadas() {
        return chamadaService.listarChamada();
    }

    @PostMapping
    public void cadastrar(@RequestBody AulaDTO aulaDTO) {
        chamadaService.cadastrarChamada(aulaDTO);
    }

    @PutMapping("/{id}")
    public void editar(@PathVariable Long id,
                       @RequestBody AulaDTO aulaDTO) {
        chamadaService.editar(id, aulaDTO);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        chamadaService.excluir(id);
    }
}