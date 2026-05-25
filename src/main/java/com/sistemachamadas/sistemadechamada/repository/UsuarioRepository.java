package com.sistemachamadas.sistemadechamada.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemachamadas.sistemadechamada.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
