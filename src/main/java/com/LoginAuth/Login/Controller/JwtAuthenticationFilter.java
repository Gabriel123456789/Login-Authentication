package com.LoginAuth.Login.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.LoginAuth.Login.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        // 1. Pega o cabeçalho "Authorization" da requisição.
        final String authHeader = request.getHeader("Authorization");

        // 2. Se não houver cabeçalho ou não começar com "Bearer ", continua para o próximo filtro.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extrai o token do cabeçalho (remove o "Bearer ").
        final String jwt = authHeader.substring(7);
        final String userEmail = jwtService.extractUsername(jwt);

        // 4. Se temos um e-mail e o usuário ainda não está autenticado no contexto de segurança atual...
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            // 5. Carrega os detalhes do usuário do banco de dados.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // 6. Verifica se o token é válido para este usuário.
            if (jwtService.isTokenValid(jwt, userDetails)) {
                
                // 7. Se for válido, cria um token de autenticação e o coloca no contexto de segurança.
                //    Isso "autentica" o usuário para esta requisição.
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, // Não precisamos de credenciais (senha) aqui
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        // 8. Passa a requisição para o próximo filtro na cadeia.
        filterChain.doFilter(request, response);
    }
}
