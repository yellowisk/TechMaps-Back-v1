package br.ifsp.techmaps.configs.auth.jwt;

import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface IAuthenticationFacade {
    Authentication getAuthentication();
    UUID getUserAuthenticatedId();
}
