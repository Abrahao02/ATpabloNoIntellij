package org.example;

public class ConflitoDatasException extends Exception {
    public ConflitoDatasException() {
        super("Conflito de datas detectado. O contrato não foi adicionado.");
    }
}
