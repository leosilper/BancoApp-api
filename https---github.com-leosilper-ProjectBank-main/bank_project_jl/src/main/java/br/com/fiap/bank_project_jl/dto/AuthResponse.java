package br.com.fiap.bank_project_jl.dto;

public class AuthResponse {
    private String token;

    // Construtor
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }

    // Setter
    public void setToken(String token) {
        this.token = token;
    }

    // toString
    @Override
    public String toString() {
        return "AuthResponse{" +
                "token='" + token + '\'' +
                '}';
    }

    // hashCode e equals, caso necessário, podem ser adicionados aqui
}
