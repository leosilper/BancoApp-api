package br.com.fiap.bank_project_jl.domain.repository;

import br.com.fiap.bank_project_jl.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
