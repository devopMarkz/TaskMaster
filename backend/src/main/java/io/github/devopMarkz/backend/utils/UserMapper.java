package io.github.devopMarkz.backend.utils;

import io.github.devopMarkz.backend.dto.user.UserRequestDTO;
import io.github.devopMarkz.backend.dto.user.UserResponseDTO;
import io.github.devopMarkz.backend.model.entities.User;
import io.github.devopMarkz.backend.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO toDTO(User user){
        return new UserResponseDTO(user.getId(), user.getNome(), user.getEmail(), user.getRole().name());
    }

    public User toEntity(UserRequestDTO requestDTO){
        return new User(
                null,
                requestDTO.nome(),
                requestDTO.email(),
                passwordEncoder.encode(requestDTO.senha()),
                Role.valueOf(requestDTO.role())
        );
    }

}
