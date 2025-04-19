package io.github.devopMarkz.backend.services.exceptions;

public class JwtInvalidException extends RuntimeException {
    public JwtInvalidException(String message) {
        super(message);
    }
}
