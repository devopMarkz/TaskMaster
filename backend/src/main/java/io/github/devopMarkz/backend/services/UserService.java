package io.github.devopMarkz.backend.services;

import io.github.devopMarkz.backend.dto.user.UserRequestDTO;
import io.github.devopMarkz.backend.dto.user.UserResponseDTO;
import io.github.devopMarkz.backend.model.entities.User;
import io.github.devopMarkz.backend.repositories.UserRepository;
import io.github.devopMarkz.backend.utils.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponseDTO insert(UserRequestDTO requestDTO){
        User user = userMapper.toEntity(requestDTO);
        User newUser = userRepository.save(user);
        return userMapper.toDTO(user);
    }

}
