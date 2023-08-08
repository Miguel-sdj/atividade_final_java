package br.edu.cesarschool.next.oo.negocio;

import br.edu.cesarschool.next.oo.dao.DAOContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.entidade.ContaPoupanca;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MediatorContaCorrente {
    
    private DAOContaCorrente daoContaCorrente = new DAOContaCorrente();

    public MediatorContaCorrente() {

    }
    
    public String incluir(ContaCorrente conta) {
        if (conta == null) {
			return "Conta não informada"; 
		} else if (stringNulaOuVazia(conta.getNumero())) {
			return "Número não informado";
		} else if (stringNulaOuVazia(conta.getNomeCorrentista())) {
			return "Nome correntista não informado"; 
		} else if (conta.getNumero().length() < 5 || conta.getNumero().length() > 8) {
			return "Tamanho do número inválido";
		} else if (conta.getNomeCorrentista().length() > 60) {
			return "Tamanho do nome do correntista inválido";		
        } else if (conta instanceof ContaPoupanca) {
            ContaPoupanca contaPoupanca = (ContaPoupanca) conta;
            if (contaPoupanca.getPercentualBonus() < 0) {
                return "Percentual de bônus inválido";
            }
        }
        conta.setDataHoraCriacao(LocalDateTime.now());
        LocalDateTime dataHora = LocalDateTime.of(2023, 8, 2, 10, 30, 0);
        // conta.setDataHoraCriacao(dataHora);
        boolean ret = daoContaCorrente.incluir(conta);
        if (!ret) {
            return "Conta corrente já existente";
        } else {
            return null;
        }
    }

    public String creditar(double valor, String numero) {
        if (valor < 0) {
            return "Valor inválido";
        } else if (stringNulaOuVazia(numero)) {
            return "Número da conta corrente não informado";
        }
        ContaCorrente conta = daoContaCorrente.buscar(numero);
        if (conta == null) {
            return "Conta corrente não encontrada";
        } else {
            conta.creditar(valor);
            daoContaCorrente.alterar(conta);
            return null;
        }
    }

    public String debitar(double valor, String numero) {
        if (valor < 0) {
            return "Valor inválido";
        } else if (stringNulaOuVazia(numero)) {
            return "Número da conta corrente não informado";
        }
        
        ContaCorrente conta = daoContaCorrente.buscar(numero);
        if (conta == null) {
            return "Conta corrente não encontrada";
        } else if (conta.getSaldo() < (valor - (valor * conta.obterAliquotaCpmf()))) {
            return "Saldo insuficiente";
        } else {
            conta.debitar(valor);
            daoContaCorrente.alterar(conta);
            return null;
        }
    }


    public ContaCorrente buscar(String numero) {
        if (stringNulaOuVazia(numero)) {
            return null;
        } else {
            return daoContaCorrente.buscar(numero);
        }
    }

    public String excluir(String numero) {
        ContaCorrente contaCorrente = daoContaCorrente.buscar(numero);
        if (contaCorrente != null) {
            if (daoContaCorrente.excluir(numero)) {
                return "Conta corrente excluída com sucesso!";
            } else {
                return "Não foi possível excluir a conta corrente.";
            }
        } else {
            return "Conta corrente não encontrada.";
        }
    }

    public List<ContaCorrente> gerarRelatorioGeral() {
        ContaCorrente[] contas = daoContaCorrente.buscarTodos();
        List<ContaCorrente> listaContas = Arrays.asList(contas);
        listaContas.sort(new ComparadorContaCorrenteSaldo());
        return listaContas;
    }

    public String excluirContasZeradas(){
        int contasExcluidas = 0;
        ContaCorrente[] contas = daoContaCorrente.buscarTodos();
        for (ContaCorrente conta : contas){
            if (conta.getSaldo() <= 0){
                daoContaCorrente.excluir(conta.getNumero());
                contasExcluidas++;
            }
        }

        if (contasExcluidas >= 1){
            return "Exclui " + contasExcluidas + " contas zeradas!";
        } else{
            return null;
        }
    }

    private boolean stringNulaOuVazia(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
