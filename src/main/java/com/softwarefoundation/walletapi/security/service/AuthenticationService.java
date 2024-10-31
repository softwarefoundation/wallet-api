package com.softwarefoundation.walletapi.security.service;

import com.softwarefoundation.walletapi.security.dto.JwtAuthenticationDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtService jwtService;

    public AuthenticationService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String authenticate(Authentication authentication){
        return this.jwtService.generateToken(authentication);
    }

}
