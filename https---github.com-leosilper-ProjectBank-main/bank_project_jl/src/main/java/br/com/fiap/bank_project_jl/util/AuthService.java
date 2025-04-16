package br.com.fiap.bank_project_jl.util;

import java.math.BigDecimal;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.bank_project_jl.domain.entity.User;
import br.com.fiap.bank_project_jl.domain.repository.UserRepository;
import br.com.fiap.bank_project_jl.dto.AuthRequest;
import br.com.fiap.bank_project_jl.dto.AuthResponse;
import br.com.fiap.bank_project_jl.security.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse login(AuthRequest request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        var token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

    public void register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já registrado");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setSaldo(BigDecimal.valueOf(1000.0)); // saldo inicial
        userRepository.save(user);
    }

    // Alternativa com AuthRequest
    public void register(AuthRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já registrado");
        }

        var user = new User();
        user.setEmail(request.getEmail());
        user.setName("Novo Usuário"); // Definir ou adaptar para DTO com nome
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setSaldo(BigDecimal.valueOf(1000.0));

        userRepository.save(user);
    }
}
