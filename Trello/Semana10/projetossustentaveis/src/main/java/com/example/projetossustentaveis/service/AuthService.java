package com.example.projetossustentaveis.service;

import com.example.projetossustentaveis.dto.JwtResponse;
import com.example.projetossustentaveis.dto.LoginRequest;
import com.example.projetossustentaveis.security.CustomUserPrincipal;
import com.example.projetossustentaveis.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getSenha()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        CustomUserPrincipal principal = (CustomUserPrincipal) userDetails;

        String token = jwtUtil.generateToken(userDetails);

        return new JwtResponse(
                token,
                userDetails.getUsername(),
                principal.getUsuario().getPerfil().name()
        );
    }
}