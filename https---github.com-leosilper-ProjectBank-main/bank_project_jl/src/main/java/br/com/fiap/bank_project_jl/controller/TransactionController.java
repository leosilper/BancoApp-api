package br.com.fiap.bank_project_jl.controller;

import br.com.fiap.bank_project_jl.domain.repository.TransactionRepository;
import br.com.fiap.bank_project_jl.domain.repository.UserRepository;
import br.com.fiap.bank_project_jl.dto.SendMoneyDTO;
import br.com.fiap.bank_project_jl.dto.TransactionDTO;
import br.com.fiap.bank_project_jl.domain.entity.User;
import br.com.fiap.bank_project_jl.domain.entity.Transaction;
import br.com.fiap.bank_project_jl.exception.InsufficientBalanceException;
import br.com.fiap.bank_project_jl.exception.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionController(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/send")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO sendMoney(@Valid @RequestBody SendMoneyDTO dto, Principal principal) {
        User remetente = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário remetente não encontrado"));
                
        User destinatario = userRepository.findByEmail(dto.getDestinatarioEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário destinatário não encontrado"));

        if (remetente.getSaldo().compareTo(dto.getValor()) < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente para completar a transação");
        }

        // Atualiza saldos
        remetente.setSaldo(remetente.getSaldo().subtract(dto.getValor()));
        destinatario.setSaldo(destinatario.getSaldo().add(dto.getValor()));

        userRepository.save(remetente);
        userRepository.save(destinatario);

        // Registra transações
        Transaction transacaoRemetente = new Transaction(
            dto.getDescricao(), 
            dto.getValor().negate(), 
            LocalDateTime.now(), 
            remetente
        );
        
        Transaction transacaoDestinatario = new Transaction(
            dto.getDescricao(), 
            dto.getValor(), 
            LocalDateTime.now(), 
            destinatario
        );

        transactionRepository.save(transacaoRemetente);
        transactionRepository.save(transacaoDestinatario);

        return TransactionDTO.fromEntity(transacaoRemetente);
    }

    @GetMapping
    public List<TransactionDTO> listar(Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
                
        return transactionRepository.findByUser(user).stream()
                .map(TransactionDTO::fromEntity)
                .toList();
    }
    
    @GetMapping("/{id}")
    public TransactionDTO getTransaction(@PathVariable Long id, Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
                
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transação não encontrada"));
                
        // Verificar se a transação pertence ao usuário
        if (!transaction.getUser().getId().equals(user.getId())) {
            throw new ResourceNotFoundException("Transação não encontrada para este usuário");
        }
        
        return TransactionDTO.fromEntity(transaction);
    }
}
