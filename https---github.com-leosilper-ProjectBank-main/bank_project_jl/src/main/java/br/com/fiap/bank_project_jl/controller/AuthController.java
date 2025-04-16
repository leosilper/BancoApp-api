package br.com.fiap.bank_project_jl.controller;

import br.com.fiap.bank_project_jl.dto.AuthRequest;
import br.com.fiap.bank_project_jl.dto.AuthResponse;
import br.com.fiap.bank_project_jl.domain.entity.User;
import br.com.fiap.bank_project_jl.util.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        authService.register(user);
    }
}
