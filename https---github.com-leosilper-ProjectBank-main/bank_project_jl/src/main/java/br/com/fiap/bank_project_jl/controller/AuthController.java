package br.com.fiap.bank_project_jl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.bank_project_jl.domain.entity.User;
import br.com.fiap.bank_project_jl.dto.AuthRequest;
import br.com.fiap.bank_project_jl.dto.AuthResponse;
import br.com.fiap.bank_project_jl.util.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        authService.register(user);
    }
}
