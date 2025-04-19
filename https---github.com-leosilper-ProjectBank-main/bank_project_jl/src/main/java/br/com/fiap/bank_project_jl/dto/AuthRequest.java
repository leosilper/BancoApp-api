package br.com.fiap.bank_project_jl.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthRequest {
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ter um formato válido")
    private String email;
    
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String password;

    // Construtor
    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter para o campo email
    public String getEmail() {
        return email;
    }

    // Setter para o campo email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter para o campo password
    public String getPassword() {
        return password;
    }

    // Setter para o campo password
    public void setPassword(String password) {
        this.password = password;
    }

    // Construtor sem argumentos (caso precise de algum lugar)
    public AuthRequest() {
    }
}
