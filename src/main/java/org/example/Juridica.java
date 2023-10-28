package org.example;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Classe Juridica
public class Juridica extends Cliente {
    private String cnpj;

    public Juridica(String nome, String telefone, String email, String cnpj) {
        super(nome, telefone, email);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCNPJ: " + cnpj;
    }
}
