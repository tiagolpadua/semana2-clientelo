package br.com.alura.clientelo.exceptions;

public class ProcessadorException extends RuntimeException {
    public ProcessadorException(String message) {
        super(message);
    }

    public ProcessadorException(String message, Throwable cause) {
        super(message, cause);
    }
}
