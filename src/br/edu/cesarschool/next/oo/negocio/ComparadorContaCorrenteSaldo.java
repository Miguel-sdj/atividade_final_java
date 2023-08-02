package br.edu.cesarschool.next.oo.negocio;

import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import java.util.Comparator;

public class ComparadorContaCorrenteSaldo implements Comparator<ContaCorrente> {

    @Override
    public int compare(ContaCorrente o1, ContaCorrente o2) {
        if (o1.getSaldo() > o2.getSaldo()) {
            return 1;
        } else if (o1.getSaldo() < o2.getSaldo()) {
            return -1;
        } else {
            return 0;
        }
    }

}
