package com.sistema.turma.repository;

import com.sistema.turma.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    boolean existsByProfessorId(Long professorId);

    boolean existsByDisciplinaId(Long disciplinaId);
}