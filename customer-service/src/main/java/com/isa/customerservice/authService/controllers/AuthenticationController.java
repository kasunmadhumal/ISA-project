package com.isa.customerservice.authService.controllers;


import com.isa.customerservice.authService.dtos.AuthenticationRequest;
import com.isa.customerservice.authService.dtos.AuthenticationResponse;
import com.isa.customerservice.authService.services.AuthenticationService;
import com.isa.customerservice.authService.dtos.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request
    ) {

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse authenticationResponse = service.authenticate(request);
        return ResponseEntity.ok(authenticationResponse);
    }



    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


}
