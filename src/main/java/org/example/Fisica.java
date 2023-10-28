package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Classe Fisica
public class Fisica extends Cliente {
    private String cpf;

    public Fisica(String nome, String telefone, String email, String cpf) {
        super(nome, telefone, email);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCPF: " + cpf;
    }
}
