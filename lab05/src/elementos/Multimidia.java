package elementos;

import static validadores.validador.*;

/**
 * Representa um elemento de dica do tipo multimídia.
 * 
 * Uma multimídia contém um link para o conteúdo, um cabeçalho
 * descritivo, a duração do conteúdo em segundos e a pontuação
 * atribuída à dica com base nesse elemento.
 * @author Yasmim Dantas
 */
public class Multimidia implements Elemento {

    private String link;
    private String cabecalho;
    private int tempo;
    private int pontuacao;

    /**
     * Constrói uma nova multimídia.
     * 
     * @param link o link para o conteúdo
     * @param cabecalho o cabeçalho descritivo da multimídia
     * @param tempo o tempo de duração do conteúdo, em segundos
     * @param pontuacao a pontuação atribuída à multimídia
     */
    public Multimidia(String link, String cabecalho, int tempo, int pontuacao) {
        verificaEntrada(link); 
        verificaEntrada(cabecalho); 
        verificaInteiro(tempo); 
        verificaInteiro(pontuacao);
        this.link = link;
        this.cabecalho = cabecalho;
        this.tempo = tempo;
        this.pontuacao = pontuacao;
    }

    /**
     * Retorna a descrição resumida de multimídia, com cabeçalho e link.
     */
    @Override
    public String getDescricaoResumida() {
        return this.cabecalho + ", " + this.link;
    }

    /**
     * Retorna a descrição resumida de multimídia, com cabeçalho, link e tempo de duração.
     */
    @Override
    public String getDescricaoDetalhada() {
        return this.cabecalho + ", " + this.link + ", " + this.tempo + "s.";
    }
    
    @Override
    public int getPontuacao() {
        return this.pontuacao;
    }

}
