package org.example;

public class FormatoCEPInvalidoException extends Exception {
    public FormatoCEPInvalidoException() {
        super("Formato de CEP inválido, deve ter 8 digitos.");
    }
}
