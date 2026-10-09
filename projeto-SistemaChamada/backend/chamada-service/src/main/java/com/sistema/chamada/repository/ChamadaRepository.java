package com.sistema.chamada.repository;

import com.sistema.chamada.model.Chamada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadaRepository extends JpaRepository<Chamada, Long> {

    List<Chamada> findByTurmaId(Long turmaId);
}