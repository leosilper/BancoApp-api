// InsufficientBalanceException.java
package br.com.fiap.bank_project_jl.exception;

public class InsufficientBalanceException extends RuntimeException {
    
    public InsufficientBalanceException(String message) {
        super(message);
    }
    
    public InsufficientBalanceException() {
        super("Saldo insuficiente para realizar a operação");
    }
}