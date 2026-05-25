package com.sistemachamadas.sistemadechamada.repository;

import com.sistemachamadas.sistemadechamada.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemachamadas.sistemadechamada.model.Presenca;

import java.util.List;

public interface PresencaRepository extends JpaRepository<Presenca, Long> {
    List<Presenca> findByAula(Aula aula);
}
