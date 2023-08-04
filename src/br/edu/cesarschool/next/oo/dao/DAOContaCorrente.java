package br.edu.cesarschool.next.oo.dao;

import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.entidade.RegistroIdentificavel;

public class DAOContaCorrente {
    private DAOGenerico daoGen = new DAOGenerico(ContaCorrente.class);

    public boolean incluir(ContaCorrente conta) {
        return daoGen.incluir(conta);
    }

    public ContaCorrente buscar(String codigo) {
        return (ContaCorrente) daoGen.buscar(codigo);
    }

    public boolean alterar(ContaCorrente conta) {
        return daoGen.alterar(conta);
    }

    public ContaCorrente[] buscarTodos() {
        RegistroIdentificavel[] rets = daoGen.buscarTodos();
        ContaCorrente[] contas = new ContaCorrente[rets.length];
        for (int i = 0; i < rets.length; i++) {
            contas[i] = (ContaCorrente) rets[i];
        }
        return contas;
    }

}
