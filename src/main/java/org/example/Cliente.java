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
// Classe Cliente
public class Cliente implements Contabil {
    private String nome;
    private String telefone;
    private String email;
    private List<ContratoAluguel> contratos = new ArrayList<>();

    public Cliente(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio.");
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone != null && !telefone.isEmpty()) {
            this.telefone = telefone;
        } else {
            throw new IllegalArgumentException("Telefone do cliente não pode ser vazio.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && !email.isEmpty()) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email do cliente não pode ser vazio.");
        }
    }

    public void adicionarContrato(ContratoAluguel contrato) {
        contratos.add(contrato);
    }

    @Override
    public double calcularValorTotalContratos() {
        double valorTotal = 0.0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataAtual = new Date(); // Obtém a data atual


        int contratosAtivos = 0;

        for (ContratoAluguel contrato : contratos) {
            try {
                Date terminoContrato = sdf.parse(contrato.getDataTermino());
                // Verifica se o contrato não está vencido (data de término é após a data atual)
                if (!terminoContrato.before(dataAtual)) {
                    valorTotal += contrato.calcularValorContrato();
                    contratosAtivos++;
                }
            } catch (ParseException e) {
                // Trate erros de parsing aqui
            }
        }
        // Aplicar desconto somente se o cliente tiver 3 ou mais contratos ativos
        if (contratosAtivos >= 5) {
            valorTotal *= 0.9; // Aplicar desconto de 10% se tiver 5 ou mais contratos ativos
        } else if (contratosAtivos >= 3) {
            valorTotal *= 0.95; // Aplicar desconto de 5% se tiver 3 ou mais contratos
        }
        return valorTotal;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nTelefone: " + telefone + "\nEmail: " + email;
    }

    // Método para verificar se um contrato está vencido com base na data atual
    public boolean contratoVencido(ContratoAluguel contrato) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataAtual = new Date(); // Obtém a data atual

        try {
            Date terminoContrato = sdf.parse(contrato.getDataTermino());
            // Verifica se o contrato está vencido (data de término é anterior à data atual)
            return terminoContrato.before(dataAtual);
        } catch (ParseException e) {
            // Trate erros de parsing aqui
            return true; // Considere como vencido em caso de erro
        }
    }
}
