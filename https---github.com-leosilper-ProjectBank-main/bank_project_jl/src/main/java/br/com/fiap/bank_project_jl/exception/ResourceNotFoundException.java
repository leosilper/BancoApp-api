// ResourceNotFoundException.java
package br.com.fiap.bank_project_jl.exception;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public ResourceNotFoundException() {
        super("Recurso não encontrado");
    }
}