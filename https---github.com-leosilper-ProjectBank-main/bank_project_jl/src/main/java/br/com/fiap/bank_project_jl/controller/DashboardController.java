package br.com.fiap.bank_project_jl.controller;

import br.com.fiap.bank_project_jl.domain.repository.TransactionRepository;
import br.com.fiap.bank_project_jl.domain.repository.UserRepository;
import br.com.fiap.bank_project_jl.dto.DashboardDTO;
import br.com.fiap.bank_project_jl.dto.TransactionDTO;
import br.com.fiap.bank_project_jl.domain.entity.User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public DashboardController(UserRepository userRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping
    public DashboardDTO getDashboard(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();

        List<TransactionDTO> ultimasTransacoes = transactionRepository
            .findTop5ByUserOrderByDataDesc(user)
            .stream()
            .map(TransactionDTO::fromEntity)
            .toList();

        DashboardDTO dashboard = new DashboardDTO();
        dashboard.setSaldo(user.getSaldo());
        dashboard.setUltimasTransacoes(ultimasTransacoes);

        return dashboard;
    }
}
