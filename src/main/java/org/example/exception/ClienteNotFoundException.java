package org.example.exception;

public class ClienteNotFoundException extends Exception {
    public ClienteNotFoundException() {
        super("Cliente não encontrado.");
    }
}
