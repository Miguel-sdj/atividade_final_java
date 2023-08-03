package br.edu.cesarschool.next.oo.apresentacao;

import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaPoupanca;
import br.edu.cesarschool.next.oo.negocio.MediatorContaCorrente;

import java.util.List;
import java.util.Scanner;

public class TelaContaCorrente {

    private static final Scanner ENTRADA = new Scanner(System.in);
    private MediatorContaCorrente mediatorContaCorrente = new MediatorContaCorrente();

    public void iniciarTela() {
        int opcao;
        do {
            System.out.println("Escolha uma opção: ");
            System.out.println("1- Incluir conta corrente");
            System.out.println("2- Creditar valor");
            System.out.println("3- Debitar valor");
            System.out.println("4- Buscar conta corrente");
            System.out.println("5- Gerar relatório geral de contas correntes");
            System.out.println("6- Sair");
            opcao = ENTRADA.nextInt();

            switch (opcao) {
                case 1:
                    incluir();
                    break;
                case 2:
                    creditar();
                    break;
                case 3:
                    debitar();
                    break;
                case 4:
                    buscar();
                    break;
                case 5:
                    gerarRelatorioGeral();
                    break;
                case 6:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (opcao != 6);
    }

    private void incluir() {
        String numero, nomeCorrentista;
        double saldo, percentualBonus = 0;
        System.out.println("Digite o número da conta corrente: ");
        numero = ENTRADA.next();
        System.out.println("Digite o saldo: ");
        saldo = ENTRADA.nextDouble();
        System.out.println("Digite o nome do correntista: ");
        nomeCorrentista = ENTRADA.next();
        System.out.println("A conta corrente é uma conta poupança? (S/N): ");
        char resposta = ENTRADA.next().toUpperCase().charAt(0);

        if (resposta == 'S') {
            System.out.println("Digite o percentual de bônus: ");
            percentualBonus = ENTRADA.nextDouble();
            ContaPoupanca contaPoupanca = new ContaPoupanca(numero, saldo, nomeCorrentista, percentualBonus);
            String mensagem = mediatorContaCorrente.incluir(contaPoupanca);
            if (mensagem == null) {
                System.out.println("Sucesso na inclusão da conta poupança!");
            } else {
                System.out.println(mensagem);
            }
        } else {
            ContaCorrente contaCorrente = new ContaCorrente(numero, saldo, nomeCorrentista);
            String mensagem = mediatorContaCorrente.incluir(contaCorrente);
            if (mensagem == null) {
                System.out.println("Sucesso na inclusão da conta corrente!");
            } else {
                System.out.println(mensagem);
            }
        }
    }

    private void creditar() {
        String numero;
        double valor;
        System.out.println("Digite o número da conta corrente: ");
        numero = ENTRADA.next();
        System.out.println("Digite o valor a ser creditado: ");
        valor = ENTRADA.nextDouble();
        String mensagem = mediatorContaCorrente.creditar(valor, numero);
        if (mensagem == null) {
            System.out.println("Sucesso no crédito!");
        } else {
            System.out.println(mensagem);
        }
    }

    private void debitar() {
        String numero;
        double valor;
        System.out.println("Digite o número da conta corrente: ");
        numero = ENTRADA.next();
        System.out.println("Digite o valor a ser debitado: ");
        valor = ENTRADA.nextDouble();
        String mensagem = mediatorContaCorrente.debitar(valor, numero);
        if (mensagem == null) {
            System.out.println("Sucesso no débito!");
        } else {
            System.out.println(mensagem);
        }
    }

    private void buscar() {
        String numero;
        System.out.println("Digite o número da conta corrente: ");
        numero = ENTRADA.next();
        ContaCorrente conta = mediatorContaCorrente.buscar(numero);
        if (conta == null) {
            System.out.println("Conta corrente não encontrada!");
        } else {
            System.out.println("Conta corrente encontrada:");
            System.out.println(conta);
        }
    }

    private void gerarRelatorioGeral() {
        List<ContaCorrente> contas = mediatorContaCorrente.gerarRelatorioGeral();
        System.out.println("Relatório Geral de Contas Correntes:");
        for (ContaCorrente conta : contas) {
            System.out.println(conta);
        }
    }

}
