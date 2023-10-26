package org.example;

public class ImovelNotFoundException extends Exception {
    public ImovelNotFoundException() {
        super("Imóvel não encontrado.");
    }
}
