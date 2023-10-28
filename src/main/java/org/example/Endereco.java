package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Classe Endereco
class Endereco {
    private String logradouro;
    private String tipoLogradouro;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(String logradouro, String tipoLogradouro, String cidade, String estado, String cep) {
        this.logradouro = logradouro;
        this.tipoLogradouro = tipoLogradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }


    @Override
    public String toString() {
        return logradouro + " " + tipoLogradouro + ", " + cidade + ", " + estado + ", " + cep;
    }
}
