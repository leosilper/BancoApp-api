package br.com.fiap.bank_project_jl.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class DashboardDTO {
    private BigDecimal saldo;
    private List<TransactionDTO> ultimasTransacoes;

    public static DashboardDTO fromUser(br.com.fiap.bank_project_jl.domain.entity.User user, List<TransactionDTO> transacoes) {
        return DashboardDTO.builder()
                .saldo(user.getSaldo())
                .ultimasTransacoes(transacoes)
                .build();
    }
}
