// CustomAuthenticationException.java (renomeado para evitar conflito)
package br.com.fiap.bank_project_jl.exception;

public class CustomAuthenticationException extends RuntimeException {
    
    public CustomAuthenticationException(String message) {
        super(message);
    }
    
    public CustomAuthenticationException() {
        super("Erro de autenticação");
    }
}