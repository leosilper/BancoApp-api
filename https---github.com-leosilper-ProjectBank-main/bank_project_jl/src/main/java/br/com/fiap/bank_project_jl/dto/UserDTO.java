package br.com.fiap.bank_project_jl.dto;

import br.com.fiap.bank_project_jl.domain.entity.User;

public class UserDTO {
    private String nome;
    private String email;

    // Construtor
    public UserDTO(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método de conversão
    public static UserDTO fromEntity(User user) {
        return new UserDTO(user.getName(), user.getEmail());
    }
}
