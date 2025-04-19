package br.com.fiap.bank_project_jl.util;

import java.math.BigDecimal;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.bank_project_jl.domain.entity.User;
import br.com.fiap.bank_project_jl.domain.repository.UserRepository;
import br.com.fiap.bank_project_jl.dto.AuthRequest;
import br.com.fiap.bank_project_jl.dto.AuthResponse;
import br.com.fiap.bank_project_jl.exception.CustomAuthenticationException;
import br.com.fiap.bank_project_jl.exception.EmailAlreadyExistsException;
import br.com.fiap.bank_project_jl.exception.ResourceNotFoundException;
import br.com.fiap.bank_project_jl.security.JwtUtil;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Construtor explícito em vez de @RequiredArgsConstructor
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse login(AuthRequest request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com e-mail: " + request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomAuthenticationException("Credenciais inválidas");
        }

        var token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

    @Transactional
    public void register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("E-mail já registrado: " + user.getEmail());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setSaldo(BigDecimal.valueOf(1000.0)); // saldo inicial para facilitar testes
        userRepository.save(user);
    }
}