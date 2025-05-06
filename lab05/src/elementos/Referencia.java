package elementos;

import static validadores.validador.*;

/**
 * Representa um elemento de dica do tipo referência bibliográfica.
 * 
 * Uma referência contém informações como título, fonte, link, ano de publicação,
 * se foi conferida, o grau de importância e a pontuação atribuída.
 * @author Yasmim Dantas
 */
public class Referencia implements Elemento {

    private String titulo;
    private String fonte;
    private String link;
    private int ano;
    private boolean conferida;
    private int importancia;
    private int pontuacao;

    /**
     * Constrói uma nova referência.
     * 
     * @param titulo o título da referência
     * @param fonte a fonte da referência (ex: nome da revista ou site)
     * @param link o link para a referência
     * @param ano o ano de publicação
     * @param statusConferida indica se a referência foi conferida
     * @param importancia valor de 1 a 5 indicando a importância da referência
     * @param pontuacao a pontuação atribuída à referência
     * @throws IllegalArgumentException se a importância não estiver entre 1 e 5
     */
    public Referencia(String titulo, String fonte, String link, int ano, boolean statusConferida, int importancia, int pontuacao) {
        verificaEntrada(link); 
        verificaEntrada(fonte); 
        verificaEntrada(link); 
        verificaInteiro(ano); 
        verificaInteiro(pontuacao);

        if (!(importancia > 0 && importancia < 6)) {
            throw new IllegalArgumentException("VALOR DE IMPORTÂNCIA INVÁLIDO");
        }

        this.titulo = titulo;
        this.fonte = fonte;
        this.link = link;
        this.ano = ano;
        this.conferida = statusConferida;
        this.importancia = importancia;
        this.pontuacao = pontuacao;
    }
    
    /**
     * Retorna a descrição resumida de referência, com fonte, link e ano.
     */
    @Override
    public String getDescricaoResumida() {
        return "Referência: " + this.titulo + ", " + this.fonte + ", " + this.link + ", " + this.ano + ".";
    }
    
    /**
     * Retorna a descrição detalhada de referência, com fonte, link, ano, se foi conferida e sua importância.
     */
    @Override
    public String getDescricaoDetalhada() {
        String status = conferida ? "Conferida" : "Não conferida";

        return "Referência: " + this.titulo + ", " + this.fonte + ", " + this.link + ", " + this.ano + 
               ".\n" + status + "\n" + "Importância: " + this.importancia;
    }
    
    @Override
    public int getPontuacao() {
        return this.pontuacao;
    }

}
