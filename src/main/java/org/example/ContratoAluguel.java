package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

// Classe ContratoAluguel
public class ContratoAluguel {
    private Imovel imovel;
    private Cliente cliente;
    private String dataInicio;
    private String dataTermino;

    public ContratoAluguel(Imovel imovel, Cliente cliente, String dataInicio, String dataTermino) throws ConflitoDatasException {
        this.imovel = imovel;
        this.cliente = cliente;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;

        // Verificar conflito de datas antes de adicionar o contrato
        if (!imovel.conflitoDeDatas(dataInicio, dataTermino)) {
            cliente.adicionarContrato(this);
            imovel.getContratos().add(this);
        } else {
            throw new ConflitoDatasException();
        }
    }

    public double calcularValorContrato() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date inicio = sdf.parse(dataInicio);
            Date termino = sdf.parse(dataTermino);

            // Calcular a diferença em meses
            int diffInMonths = calcularDiferencaMeses(inicio, termino);

            double valorContrato = imovel.getValorAluguel() * diffInMonths;

            return valorContrato;
        } catch (ParseException e) {
            // Trate erros de parsing aqui
            return 0.0; // Em caso de erro, retorna 0
        }
    }

    // Método para calcular a diferença em meses entre duas datas
    private int calcularDiferencaMeses(Date inicio, Date termino) {
        Calendar calInicio = new GregorianCalendar();
        calInicio.setTime(inicio);
        Calendar calTermino = new GregorianCalendar();
        calTermino.setTime(termino);

        int anoInicio = calInicio.get(Calendar.YEAR);
        int mesInicio = calInicio.get(Calendar.MONTH);
        int anoTermino = calTermino.get(Calendar.YEAR);
        int mesTermino = calTermino.get(Calendar.MONTH);

        return (anoTermino - anoInicio) * 12 + (mesTermino - mesInicio);
    }

    public boolean contratoVencido() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataAtual = new Date(); // Obtém a data atual

        try {
            Date terminoContrato = sdf.parse(dataTermino);
            // Verifica se o contrato está vencido (data de término é anterior à data atual)
            return terminoContrato.before(dataAtual);
        } catch (ParseException e) {
            // Trate erros de parsing aqui
            return true; // Considere como vencido em caso de erro
        }
    }

    public Imovel getImovel() {
        return imovel;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    @Override
    public String toString() {
        String status = contratoVencido() ? "Vencido" : "Não Vencido";
        return imovel.getRegistro() + "," + imovel.getNome() + "," + imovel.getEndereco() + "," + imovel.getValorAluguel()
                + "," + cliente.getNome() + "," + cliente.getTelefone() + "," + dataInicio + "," + dataTermino +
                "," + status;
    }
}
