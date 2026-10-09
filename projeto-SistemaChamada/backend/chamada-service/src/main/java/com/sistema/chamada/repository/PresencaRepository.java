package com.sistema.chamada.repository;

import com.sistema.chamada.model.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PresencaRepository extends JpaRepository<Presenca, Long> {

    Optional<Presenca> findByChamadaIdAndAlunoId(
            Long chamadaId,
            Long alunoId
    );

    List<Presenca> findByChamadaIdIn(List<Long> chamadaIds);
}