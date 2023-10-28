package org.example.exception;

public class ImovelNotFoundException extends Exception {
    public ImovelNotFoundException() {
        super("Imóvel não encontrado.");
    }
}
