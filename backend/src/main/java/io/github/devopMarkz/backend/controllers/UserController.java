package io.github.devopMarkz.backend.controllers;

import io.github.devopMarkz.backend.dto.auth.AuthDTO;
import io.github.devopMarkz.backend.dto.token.TokenDTO;
import io.github.devopMarkz.backend.dto.user.UserRequestDTO;
import io.github.devopMarkz.backend.dto.user.UserResponseDTO;
import io.github.devopMarkz.backend.services.TokenService;
import io.github.devopMarkz.backend.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static io.github.devopMarkz.backend.utils.UriGenerator.generateUri;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private TokenService tokenService;

    public UserController(AuthenticationManager authenticationManager, UserService userService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUsuario(@Valid @RequestBody UserRequestDTO requestDTO){
        UserResponseDTO responseDTO = userService.insert(requestDTO);
        URI location = generateUri(responseDTO.id());
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody AuthDTO authDTO){
        log.info("Tentativa de login efetuada por {}", authDTO.email());
        var authentication = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.senha());
        authenticationManager.authenticate(authentication);
        return ResponseEntity.ok(new TokenDTO(tokenService.getToken(authDTO)));
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String test(){
        return "Autenticado.";
    }

}
