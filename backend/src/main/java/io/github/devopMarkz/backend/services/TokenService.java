package io.github.devopMarkz.backend.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import io.github.devopMarkz.backend.dto.auth.AuthDTO;
import io.github.devopMarkz.backend.model.entities.User;
import io.github.devopMarkz.backend.repositories.UserRepository;
import io.github.devopMarkz.backend.services.exceptions.JwtInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    public String tokenValidate(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.require(algorithm)
                    .withIssuer("TaskM-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTCreationException e) {
            throw new JwtInvalidException("Token inválido.");
        }
    }

    public String getToken(AuthDTO authDTO){
        User user = userRepository.findByEmail(authDTO.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário inexistente"));
        return tokenGenerate(user);
    }

    private String tokenGenerate(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withIssuer("TaskM-api")
                    .withSubject(user.getEmail())
                    .withClaim("Role", user.getRole().name())
                    .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                    .sign(algorithm);
        } catch (JWTCreationException jwtCreationException){
            throw new JwtInvalidException("Erro ao gerar Token JWT");
        }
    }

}
