package io.github.devopMarkz.backend.config;

import io.github.devopMarkz.backend.model.entities.User;
import io.github.devopMarkz.backend.repositories.UserRepository;
import io.github.devopMarkz.backend.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityAuthentication extends OncePerRequestFilter {

    private UserRepository userRepository;
    private TokenService tokenService;

    public SecurityAuthentication(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = tokenExtract(request);

        if(token != null){
            String login = tokenService.tokenValidate(token);
            User usuario = userRepository.findByEmail(login)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String tokenExtract(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");

        if(authHeader == null) return null;
        if(!authHeader.split(" ")[0].equals("Bearer")) return null;

        return authHeader.split(" ")[1];
    }
}
