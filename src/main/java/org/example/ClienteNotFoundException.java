package org.example;

public class ClienteNotFoundException extends Exception {
    public ClienteNotFoundException() {
        super("Cliente não encontrado.");
    }
}
