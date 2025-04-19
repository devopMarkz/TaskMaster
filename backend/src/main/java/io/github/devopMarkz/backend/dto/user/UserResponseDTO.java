package io.github.devopMarkz.backend.dto.user;

public record UserResponseDTO(
        Long id,
        String nome,
        String email,
        String role
) {}