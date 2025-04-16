package br.com.fiap.bank_project_jl.dto;

import br.com.fiap.bank_project_jl.domain.entity.Transaction;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime data;

    public static TransactionDTO fromEntity(Transaction t) {
        return TransactionDTO.builder()
                .id(t.getId())
                .descricao(t.getDescricao())
                .valor(t.getValor())
                .data(t.getData())
                .build();
    }
}
