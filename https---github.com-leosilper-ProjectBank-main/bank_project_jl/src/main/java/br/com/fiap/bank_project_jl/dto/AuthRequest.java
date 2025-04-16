package br.com.fiap.bank_project_jl.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
