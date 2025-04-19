package br.com.fiap.bank_project_jl.dto;

import br.com.fiap.bank_project_jl.domain.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime data;

    // Construtor
    public TransactionDTO(Long id, String descricao, BigDecimal valor, LocalDateTime data) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    // Método de conversão
    public static TransactionDTO fromEntity(Transaction t) {
        return new TransactionDTO(t.getId(), t.getDescricao(), t.getValor(), t.getData());
    }
}
