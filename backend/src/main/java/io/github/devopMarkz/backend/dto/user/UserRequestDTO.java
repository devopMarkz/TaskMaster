package io.github.devopMarkz.backend.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
        @NotBlank(message = "Nome não pode ser vazio")
        String nome,

        @NotBlank(message = "Email não pode ser vazio")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "Senha não pode ser vazia")
        String senha,

        @NotBlank(message = "Role não pode ser nulo nem vazio")
        String role
) {}
