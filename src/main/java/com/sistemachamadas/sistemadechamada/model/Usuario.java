package com.sistemachamadas.sistemadechamada.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Column (unique = true)
    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;
}