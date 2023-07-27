package br.ifsp.techmaps.configs.auth.jwt;

import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtProperties jwtProperties;
    private final JwtTokenHelper jwtTokenHelper;

    public JwtTokenVerifier(JwtProperties jwtProperties, JwtTokenHelper jwtTokenHelper) {
        this.jwtProperties = jwtProperties;
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if(isFromPermittedPath(request)) {
            filterChain.doFilter(request, response);
            System.out.println("Permitted path" + request.getHeaderNames());
            System.out.println("Permitted path" + request.getServletPath());
            System.out.println("Permitted path" + request.getHeader("Authorization"));
            return;
        }

        final String authorizationHeader = request.getHeader(jwtProperties.getAuthorizationHeader());
        System.out.println("Authorization header: " + authorizationHeader);
        if (jwtTokenHelper.hasInvalidAuthorization(authorizationHeader)) {
            final String error = "Authorization header is missing or invalid.";
            log.error("Token verification error: {}", error);
            System.out.println("Permitted path" + request.getHeaderNames());
            System.out.println("Permitted path" + request.getServletPath());
            System.out.println("request.getHeader" + request.getHeader("Authorization"));
            response.addHeader(jwtProperties.getAuthorizationHeader(), error);
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authorizationHeader.replace(jwtProperties.getTokenPrefix(), "");
        try {
            final String principal = jwtTokenHelper.extractClaims(token).getSubject();
            final UUID userId = UUID.fromString(principal);
            final var authentication = new UsernamePasswordAuthenticationToken(userId, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            log.error("Token verification error: {}", e.getMessage());
            jwtTokenHelper.configureTokenErrorResponse(response, e.getMessage());
        }
    }

    private boolean isFromPermittedPath(HttpServletRequest request) {
        return request.getServletPath().equals("/register")
                || request.getServletPath().equals("/login")
                || request.getServletPath().equals("/refresh-token");
    }
}
