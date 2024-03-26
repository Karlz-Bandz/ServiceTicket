package com.ncr.serviceticket.controller;

import com.ncr.serviceticket.dto.AuthenticationDto;
import com.ncr.serviceticket.dto.TokenDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthController {

    @PostMapping("/authenticate")
    ResponseEntity<TokenDto> authenticate(@RequestBody AuthenticationDto request);
}
