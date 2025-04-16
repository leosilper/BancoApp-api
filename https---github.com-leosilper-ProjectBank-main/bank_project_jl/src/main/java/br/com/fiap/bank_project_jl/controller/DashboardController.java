package br.com.fiap.bank_project_jl.controller;

import br.com.fiap.bank_project_jl.domain.repository.TransactionRepository;
import br.com.fiap.bank_project_jl.domain.repository.UserRepository;
import br.com.fiap.bank_project_jl.dto.DashboardDTO;
import br.com.fiap.bank_project_jl.dto.TransactionDTO;
import br.com.fiap.bank_project_jl.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @GetMapping
    public DashboardDTO getDashboard(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();

        var ultimasTransacoes = transactionRepository
            .findTop5ByUserOrderByDataDesc(user)
            .stream()
            .map(TransactionDTO::fromEntity)
            .toList();

        return DashboardDTO.builder()
                .saldo(user.getSaldo())
                .ultimasTransacoes(ultimasTransacoes)
                .build();
    }
}
