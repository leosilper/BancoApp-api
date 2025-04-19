// EmailAlreadyExistsException.java
package br.com.fiap.bank_project_jl.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
    
    public EmailAlreadyExistsException() {
        super("Email já está cadastrado no sistema");
    }
}