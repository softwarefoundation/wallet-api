package com.softwarefoundation.walletapi.security.controller;

import com.softwarefoundation.walletapi.response.Response;
import com.softwarefoundation.walletapi.security.dto.TokenDTO;
import com.softwarefoundation.walletapi.security.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<Response<TokenDTO>> authenticate(Authentication authentication)
            throws AuthenticationException {

        Response<TokenDTO> response = new Response<>();
        String jwt = this.authenticationService.authenticate(authentication);
        response.setData(new TokenDTO(jwt));
        return ResponseEntity.ok(response);
    }


}
