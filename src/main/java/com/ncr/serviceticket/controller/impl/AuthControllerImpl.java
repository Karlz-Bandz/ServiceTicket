package com.ncr.serviceticket.controller.impl;

import com.ncr.serviceticket.configuration.security.jwt.JwtUtils;
import com.ncr.serviceticket.controller.AuthController;
import com.ncr.serviceticket.dto.AuthenticationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtUtils jwtUtils;

    @Override
    public ResponseEntity<AuthenticationDto> authenticate(AuthenticationDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        final UserDetails user = userDetailsService.loadUserByUsername(request.email());

        if (user != null) {
            AuthenticationDto tokenDto = AuthenticationDto.builder()
                    .token(jwtUtils.generateToken(user))
                    .build();
            return ResponseEntity.ok(tokenDto);
        } else {
            return ResponseEntity.status(400).body(AuthenticationDto.builder().token("").build());
        }
    }
}
