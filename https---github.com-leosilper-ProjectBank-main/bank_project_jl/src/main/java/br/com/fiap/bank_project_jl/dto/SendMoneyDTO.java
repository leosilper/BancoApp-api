package br.com.fiap.bank_project_jl.dto;

import java.math.BigDecimal;

public class SendMoneyDTO {

    private String destinatarioEmail;
    private BigDecimal valor;
    private String descricao;

    public String getDestinatarioEmail() { return destinatarioEmail; }
    public void setDestinatarioEmail(String destinatarioEmail) { this.destinatarioEmail = destinatarioEmail; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
