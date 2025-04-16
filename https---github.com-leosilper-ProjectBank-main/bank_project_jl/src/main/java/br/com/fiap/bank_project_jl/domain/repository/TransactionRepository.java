package br.com.fiap.bank_project_jl.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.bank_project_jl.domain.entity.Transaction; // ✅ sua entidade correta
import br.com.fiap.bank_project_jl.domain.entity.User;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTop5ByUserOrderByDataDesc(User user);
    List<Transaction> findByUser(User user);
}
