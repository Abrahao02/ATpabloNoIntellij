package org.example;

public class FormatoCPFInvalidoException extends Exception {
    public FormatoCPFInvalidoException() {
        super("Formato de CPF/CNPJ inválido.");
    }
}
