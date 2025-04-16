package br.com.fiap.bank_project_jl.controller;

import br.com.fiap.bank_project_jl.domain.repository.UserRepository;
import br.com.fiap.bank_project_jl.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public UserDTO getProfile(Principal principal) {
        return userRepository.findByEmail(principal.getName())
                .map(UserDTO::fromEntity)
                .orElseThrow();
    }
}
