package complementacao;

import java.util.*;

import elementos.Elemento;
import elementos.Multimidia;
import elementos.Referencia;
import elementos.Texto;

import static validadores.validador.*;

/**
 * Representa uma dica criada por um usuário com base em um tema e composta por vários elementos.
 * @author Yasmim Dantas
 */
public class Dica {

    private Usuario usuario;
    private String tema;
    private List<Elemento> elementos;

    /**
     * Construtor da dica.
     *
     * @param usuario Usuário autor da dica.
     * @param tema Tema da dica.
     */
    public Dica(Usuario usuario, String tema) {
        verificaUsuario(usuario); 
        verificaEntrada(tema);

        this.usuario = usuario;
        this.tema = tema;
        this.elementos = new ArrayList<Elemento>();
    }

    /**
     * Adiciona um elemento de texto à dica.
     *
     * @param texto Conteúdo textual.
     * @param pontuacao Pontuação atribuída ao elemento.
     */
    public void adicionaElementoTexto(String texto, int pontuacao) {
        verificaEntrada(texto); 
        verificaInteiro(pontuacao);

        Elemento novo = new Texto(texto, pontuacao);
        elementos.add(novo);
    }

    /**
     * Adiciona um elemento de multimídia à dica.
     *
     * @param link Link do conteúdo.
     * @param cabecalho Cabeçalho ou título da multimídia.
     * @param tempo Tempo de duração (em minutos).
     * @param pontuacao Pontuação atribuída ao elemento.
     */
    public void adicionaElementoMultimidia(String link, String cabecalho, int tempo, int pontuacao) {
        verificaEntrada(link); 
        verificaEntrada(cabecalho); 
        verificaInteiro(tempo); 
        verificaInteiro(pontuacao);

        Elemento novo = new Multimidia(link, cabecalho, tempo, pontuacao);
        elementos.add(novo);
    }

    /**
     * Adiciona um elemento de referência à dica.
     *
     * @param titulo Título da referência.
     * @param fonte Fonte da publicação.
     * @param link Link da referência.
     * @param ano Ano da publicação.
     * @param conferida Indica se a referência foi conferida.
     * @param relevancia Relevância (de 1 a 5).
     * @param pontuacao Pontuação atribuída ao elemento.
     */
    public void adicionaElementoReferencia(String titulo, String fonte, String link, int ano, boolean conferida, int relevancia, int pontuacao) {
        verificaEntrada(titulo); 
        verificaEntrada(fonte); 
        verificaInteiro(pontuacao); 
        verificaInteiro(ano);

        if (relevancia < 1 || relevancia > 5) {
            throw new IllegalArgumentException("RELEVÂNCIA FORA DO INTERVALO PERMITIDO!");
        }

        Elemento novo = new Referencia(titulo, fonte, link, ano, conferida, relevancia, pontuacao);
        elementos.add(novo);
    }

    /**
     * Retorna uma descrição resumida da dica, com base nos elementos.
     *
     * @return String com a descrição resumida da dica.
     */
    public String getDicaResumida() {
        String retorno = "Autor: " + this.usuario.getNome() + "\n";

        for (Elemento e : elementos) {
            retorno += e.getDescricaoResumida() + "\n";
        }

        return retorno;
    }

    /**
     * Retorna uma descrição detalhada da dica, com base nos elementos.
     *
     * @return String com a descrição detalhada da dica.
     */
    public String getDicaDetalhada() {
        String retorno = "Autor: " + this.usuario.getNome() + "\n";

        for (Elemento e : elementos) {
            retorno += e.getDescricaoDetalhada() + "\n";
        }

        return retorno;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public String getTema() {
        return tema;
    }

}
