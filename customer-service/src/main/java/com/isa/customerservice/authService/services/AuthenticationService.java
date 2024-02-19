package com.isa.customerservice.authService.services;

import com.isa.customerservice.authService.dtos.AuthenticationRequest;
import com.isa.customerservice.authService.dtos.AuthenticationResponse;
import com.isa.customerservice.authService.dtos.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {

    String register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
