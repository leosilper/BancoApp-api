package br.com.fiap.bank_project_jl.controller;

import br.com.fiap.bank_project_jl.domain.repository.TransactionRepository;
import br.com.fiap.bank_project_jl.domain.repository.UserRepository;
import br.com.fiap.bank_project_jl.dto.SendMoneyDTO;
import br.com.fiap.bank_project_jl.dto.TransactionDTO;
import br.com.fiap.bank_project_jl.domain.entity.User;
import br.com.fiap.bank_project_jl.domain.entity.Transaction; // ✅ SUA ENTIDADE

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional; // ✅ anotação do Spring
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @PostMapping("/send")
    @Transactional // ✅ anotação correta agora
    public void sendMoney(@RequestBody SendMoneyDTO dto, Principal principal) {
        User remetente = userRepository.findByEmail(principal.getName()).orElseThrow();
        User destinatario = userRepository.findByEmail(dto.getDestinatarioEmail()).orElseThrow();

        if (remetente.getSaldo().compareTo(dto.getValor()) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        // Atualiza saldos
        remetente.setSaldo(remetente.getSaldo().subtract(dto.getValor()));
        destinatario.setSaldo(destinatario.getSaldo().add(dto.getValor()));

        userRepository.save(remetente);
        userRepository.save(destinatario);

        // Registra transações
        Transaction t1 = new Transaction(dto.getDescricao(), dto.getValor().negate(), LocalDateTime.now(), remetente);
        Transaction t2 = new Transaction(dto.getDescricao(), dto.getValor(), LocalDateTime.now(), destinatario);

        transactionRepository.save(t1);
        transactionRepository.save(t2);
    }

    @GetMapping
    public List<TransactionDTO> listar(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        return transactionRepository.findByUser(user).stream()
                .map(TransactionDTO::fromEntity)
                .toList();
    }
}
