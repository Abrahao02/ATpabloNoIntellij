package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;



public class Main {
    private static List<Imovel> imoveis = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<ContratoAluguel> contratos = new ArrayList<>();

    public static void main(String[] args) {
        Cliente clienteEncontrado = null;
        boolean imovelEncontrado = false;
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Menu Principal:");
                System.out.println("1. Adicionar Cliente");
                System.out.println("2. Adicionar Imóvel");
                System.out.println("3. Adicionar Contrato de Aluguel");
                System.out.println("4. Buscar Cliente e Listar Contratos");
                System.out.println("5. Buscar Imóvel e Listar Contratos");
                System.out.println("6. Exportar contratos em formato CSV");
                System.out.println("7. Sair");

                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                switch (opcao) {
                    case 1:
                        // Adicionar Cliente
                        System.out.print("Nome do Cliente: ");
                        String nomeCliente = scanner.nextLine();
                        System.out.print("Telefone do Cliente: ");
                        String telefoneCliente = scanner.nextLine();
                        System.out.print("Email do Cliente: ");
                        String emailCliente = scanner.nextLine();
                        System.out.print("CPF ou CNPJ do Cliente: ");
                        String cpfOuCnpj = scanner.nextLine();


                        if (cpfOuCnpj.length() == 11) {
                            clientes.add(new Fisica(nomeCliente, telefoneCliente, emailCliente, cpfOuCnpj));
                            System.out.println("Cliente adicionado com sucesso.");
                        } else if (cpfOuCnpj.length() == 14) {
                            clientes.add(new Juridica(nomeCliente, telefoneCliente, emailCliente, cpfOuCnpj));
                            System.out.println("Cliente adicionado com sucesso.");


                        } else {
                            try{
                                throw new FormatoCPFInvalidoException();
                            } catch (FormatoCPFInvalidoException e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }

                        break;

                    case 2:
                        // Adicionar Imóvel
                        System.out.print("Número de Registro do Imóvel: ");
                        int registro = scanner.nextInt();
                        scanner.nextLine(); // Consumir a quebra de linha
                        boolean registroExistente = false;
                        for (Imovel imovelExistente : imoveis) {
                            if (imovelExistente.getRegistro() == registro) {
                                registroExistente = true;
                                break;
                            }
                        }

                        if (registroExistente) {
                            System.out.println("Número de registro já existente. Escolha outro número.");
                        } else {
                            System.out.print("Nome do Imóvel: ");
                            String nomeImovel = scanner.nextLine();

                            System.out.print("Rua: ");
                            String logradouro = scanner.nextLine();

                            System.out.print("Numero: ");
                            String tipoLogradouro = scanner.nextLine();

                            System.out.print("Cidade: ");
                            String cidade = scanner.nextLine();

                            System.out.print("Estado: ");
                            String estado = scanner.nextLine();

                            System.out.print("CEP: ");
                            String cep = scanner.nextLine();
                            if (cep.length() != 8){
                                try {
                                    throw new FormatoCEPInvalidoException();
                                } catch (FormatoCEPInvalidoException e) {
                                    System.out.println("Erro: " + e.getMessage());
                                    break;
                                }
                            }

                            // Solicitar ao usuário que escolha o tipo de imóvel
                            System.out.print("Tipo do Imóvel (RESIDENCIAL ou COMERCIAL): ");
                            String tipoImovelStr = scanner.nextLine();
                            TipoImovelEnum tipoImovel;

                            // Verificar se o tipo de imóvel é válido
                            if (tipoImovelStr.equalsIgnoreCase("RESIDENCIAL")) {
                                tipoImovel = TipoImovelEnum.RESIDENCIAL;
                            } else if (tipoImovelStr.equalsIgnoreCase("COMERCIAL")) {
                                tipoImovel = TipoImovelEnum.COMERCIAL;
                            } else {
                                System.out.println("Tipo de imóvel inválido.");
                                tipoImovel = TipoImovelEnum.RESIDENCIAL;
                            }

                            System.out.print("Valor Mensal de Aluguel: ");
                            double valorAluguel = scanner.nextDouble();

                            // Crie uma instância de "Imovel" usando o novo construtor
                            imoveis.add(new Imovel(registro, nomeImovel, logradouro, tipoLogradouro, cidade, estado, cep, tipoImovel, valorAluguel));
                            System.out.println("Imóvel adicionado com sucesso.");
                        }
                        break;



                    case 3:
                        // Adicionar Contrato de Aluguel
                        System.out.print("Número de Registro do Imóvel: ");
                        int registroImovel = scanner.nextInt();
                        scanner.nextLine(); // Consumir a quebra de linha

                        // Verificar se o número de registro do imóvel já existe

                        for (Imovel imovelExistente : imoveis) {
                            if (imovelExistente.getRegistro() == registroImovel) {
                                imovelEncontrado = true;
                                break;
                            }
                        }

                        if (!imovelEncontrado) {
                            try {
                                throw new ImovelNotFoundException();
                            } catch (ImovelNotFoundException e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                            break; // Sair do caso se o registro não for encontrado
                        }

                        System.out.print("CPF ou CNPJ do Cliente: ");
                        String cpfOuCnpjCliente = scanner.nextLine();

                        // Verificar se o CPF ou CNPJ é válido
                        for (Cliente cliente : clientes) {
                            if ((cliente instanceof Fisica && ((Fisica) cliente).getCpf().equals(cpfOuCnpjCliente)) ||
                                    (cliente instanceof Juridica && ((Juridica) cliente).getCnpj().equals(cpfOuCnpjCliente))) {
                                clienteEncontrado = cliente;
                                break;
                            }
                        }

                        if (clienteEncontrado == null) {
                            try {
                                throw new ClienteNotFoundException();
                            } catch (ClienteNotFoundException e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                            break; // Sair do caso se o cliente não for encontrado
                        }

                        System.out.print("Data de Início do Contrato (DD/MM/AAAA): ");
                        String dataInicio = scanner.nextLine();
                        System.out.print("Data de Término do Contrato (DD/MM/AAAA): ");
                        String dataTermino = scanner.nextLine();

                        boolean sobreposicao = false;

                        // Verificar sobreposição de datas
                        for (ContratoAluguel contratoExistente : contratos) {
                            if (contratoExistente.getImovel().getRegistro() == registroImovel) {
                                String dataInicioExistente = contratoExistente.getDataInicio();
                                String dataTerminoExistente = contratoExistente.getDataTermino();

                                if ((dataInicio.compareTo(dataInicioExistente) >= 0 && dataInicio.compareTo(dataTerminoExistente) <= 0) ||
                                        (dataTermino.compareTo(dataInicioExistente) >= 0 && dataTermino.compareTo(dataTerminoExistente) <= 0)) {
                                    sobreposicao = true;
                                    break;
                                }
                            }
                        }

                        if (sobreposicao) {
                            System.out.println("Conflito de datas: As datas se sobrepõem com um contrato existente.");
                        } else {
                            // Se não houver sobreposição, adicione o contrato
                            for (Imovel imovel : imoveis) {
                                if (imovel.getRegistro() == registroImovel) {
                                    try {
                                        ContratoAluguel contrato = new ContratoAluguel(imovel, clienteEncontrado, dataInicio, dataTermino);
                                        contratos.add(contrato);
                                        System.out.println("Contrato de aluguel adicionado com sucesso.");
                                    } catch (ConflitoDatasException e) {
                                        System.out.println("Erro ao adicionar contrato: " + e.getMessage());
                                    }
                                    break;
                                }
                            }
                        }
                        break;



                    case 4:
                        // Buscar Cliente e Listar Contratos
                        System.out.print("CPF ou CNPJ do Cliente: ");
                        String cpfOuCnpjBusca = scanner.nextLine();
                        for (Cliente cliente : clientes) {
                            if ((cliente instanceof Fisica && ((Fisica) cliente).getCpf().equals(cpfOuCnpjBusca)) ||
                                    (cliente instanceof Juridica && ((Juridica) cliente).getCnpj().equals(cpfOuCnpjBusca))) {
                                System.out.println("Contratos do Cliente:");
                                for (ContratoAluguel contrato : contratos) {
                                    if (contrato.getCliente() == cliente) {
                                        System.out.println(contrato);
                                    }
                                }
                                System.out.println("Valor Total a Pagar: R$" + cliente.calcularValorTotalContratos());
                                break;
                            }
                        }
                        if (clienteEncontrado == null) {
                            try {
                                throw new ClienteNotFoundException();
                            } catch (ClienteNotFoundException e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }
                        break;

                    case 5:
                        // Buscar Imóvel e Listar Contratos
                        System.out.print("Número de Registro do Imóvel: ");
                        int registroBusca = scanner.nextInt();
                        scanner.nextLine(); // Consumir a quebra de linha


                        for (Imovel imovel : imoveis) {
                            if (imovel.getRegistro() == registroBusca) {
                                System.out.println("Contratos do Imóvel:");
                                for (ContratoAluguel contrato : contratos) {
                                    if (contrato.getImovel() == imovel) {
                                        System.out.println(contrato);
                                    }
                                }
                                imovelEncontrado = true;
                                break;
                            }
                        }

                        if (!imovelEncontrado) {
                            try {
                                throw new ImovelNotFoundException();
                            } catch (ImovelNotFoundException e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }
                        break;

                    case 6:
                        // Exportar Contratos em formato CSV
                        exportarContratosCSV();
                        break;

                    case 7:
                        System.out.println("Programa encerrado.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            }
        }
    }
    // Função para exportar contratos em formato CSV
    private static void exportarContratosCSV() {
        try {
            FileWriter writer = new FileWriter("contratos.csv");
            writer.write("Registro,Imóvel,Endereço,Valor Aluguel,Cliente,Telefone,Data Início,Data Término,Status\n");

            for (ContratoAluguel contrato : contratos) {
                String linha = contrato.toString();
                writer.write(linha + "\n");
            }

            writer.close();
            System.out.println("Contratos exportados com sucesso para 'contratos.csv'.");
        } catch (IOException e) {
            System.out.println("Erro ao exportar contratos.");
            e.printStackTrace();
        }
    }

}