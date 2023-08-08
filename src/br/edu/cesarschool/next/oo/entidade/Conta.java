package br.edu.cesarschool.next.oo.entidade;

import java.math.BigDecimal;

public abstract class Conta extends RegistroIdentificavel {
    public abstract double obterAliquotaCpmf();
    
    public double debitar(double saldo, double valor) {
        double valorCpmf = valor - (valor * obterAliquotaCpmf()); 
        return saldo - valorCpmf;
    }
}


// saldo de 10 debitando 9 ficaria 1 agora deve ficar 3,7
