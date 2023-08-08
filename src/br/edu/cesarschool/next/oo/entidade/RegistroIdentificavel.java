package br.edu.cesarschool.next.oo.entidade;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public abstract class RegistroIdentificavel implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDateTime dataHoraCriacao;

    // public RegistroIdentificavel() {
    //     dataHoraCriacao = LocalDateTime.now();
    // }
    
    // public int obterTempoDeCriacao() {
    //     LocalDateTime dataAtual = LocalDateTime.now();
    //     return dataHoraCriacao.toLocalDate().until(dataAtual.toLocalDate()).getDays();
    // }

    public int obterTempoDeCriacao() {
        LocalDateTime dataAtual = LocalDateTime.now();
        Duration duracao = Duration.between(dataHoraCriacao, dataAtual);
        int diferencaEmDias = (int)duracao.toDays();
        return diferencaEmDias;
    }


    public abstract String obterChave();

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }
    
}
