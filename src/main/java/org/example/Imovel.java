package org.example;

import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
// Classe Imovel
public class Imovel {
    private int registro;
    private String nome;
    private Endereco endereco;
    private TipoImovelEnum tipo;
    private double valorAluguel;
    private List<ContratoAluguel> contratos = new ArrayList<>();

    public Imovel(int registro, String nome, String logradouro, String tipoLogradouro, String cidade, String estado, String cep, TipoImovelEnum tipo, double valorAluguel) {
        this.registro = registro;
        this.nome = nome;
        this.endereco = new Endereco(logradouro, tipoLogradouro, cidade, estado, cep);
        this.tipo = tipo;
        this.valorAluguel = valorAluguel;
    }


    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public double getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public List<ContratoAluguel> getContratos() {
        return contratos;
    }

    public boolean conflitoDeDatas(String dataInicio, String dataTermino) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date inicioNovoContrato;
        Date terminoNovoContrato;

        try {
            inicioNovoContrato = sdf.parse(dataInicio);
            terminoNovoContrato = sdf.parse(dataTermino);
        } catch (ParseException e) {
            // Trate erros de parsing aqui
            return true; // Considere como conflito em caso de erro
        }

        Date inicioContratoExistente;
        Date terminoContratoExistente;
        for (ContratoAluguel contrato : contratos) {
            try {
                inicioContratoExistente = sdf.parse(contrato.getDataInicio());
                terminoContratoExistente = sdf.parse(contrato.getDataTermino());

                // Verifica se há sobreposição de datas
                if ((inicioNovoContrato.before(terminoContratoExistente) || inicioNovoContrato.equals(terminoContratoExistente)) &&
                        (terminoNovoContrato.after(inicioContratoExistente) || terminoNovoContrato.equals(inicioContratoExistente))) {
                    return true; // Há conflito de datas
                }
            } catch (ParseException e) {
                // Trate erros de parsing aqui
                return true; // Considere como conflito em caso de erro
            }
        }

        return false; // Não há conflito de datas
    }

    @Override
    public String toString() {
        return "Registro: " + registro + "\nNome: " + nome + "\nEndereço: " + endereco + "\nTipo: " + tipo +
                "\nValor do Aluguel: R$" + valorAluguel;
    }
}
