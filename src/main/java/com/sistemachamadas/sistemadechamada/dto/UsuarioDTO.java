package com.sistemachamadas.sistemadechamada.dto;

import com.sistemachamadas.sistemadechamada.model.TipoUsuario;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, message = "Nome deve ter no mínimo 3 letras")
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotNull(message = "Tipo de usuário é obrigatório")
    private TipoUsuario tipo;
}