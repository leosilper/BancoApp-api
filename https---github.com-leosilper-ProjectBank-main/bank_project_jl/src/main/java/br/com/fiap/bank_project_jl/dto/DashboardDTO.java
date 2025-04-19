package br.com.fiap.bank_project_jl.dto;

import java.math.BigDecimal;
import java.util.List;

public class DashboardDTO {

    private BigDecimal saldo;
    private List<TransactionDTO> ultimasTransacoes;

    public DashboardDTO() {}

    public DashboardDTO(BigDecimal saldo, List<TransactionDTO> ultimasTransacoes) {
        this.saldo = saldo;
        this.ultimasTransacoes = ultimasTransacoes;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<TransactionDTO> getUltimasTransacoes() {
        return ultimasTransacoes;
    }

    public void setUltimasTransacoes(List<TransactionDTO> ultimasTransacoes) {
        this.ultimasTransacoes = ultimasTransacoes;
    }

    public static DashboardDTO fromUser(br.com.fiap.bank_project_jl.domain.entity.User user, List<TransactionDTO> transacoes) {
        return new DashboardDTO(user.getSaldo(), transacoes);
    }
}
