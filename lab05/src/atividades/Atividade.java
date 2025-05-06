package atividades;

import static validadores.validador.*;

/**
 * Representa uma atividade complementar genérica.
 * 
 * É uma classe abstrata que serve como base para atividades específicas como
 * estágio, monitoria, pesquisa/extensão e representação estudantil.
 * @author Yasmim Dantas
 */
public abstract class Atividade {
    
    protected String tipo;
    protected String descricao;
    protected String codigo;
    protected String linkDocumentacao;
    protected int tempo;
    protected int creditos;
    protected int limiteCreditos;
    protected String unidadeTempo;

    /**
     * Constrói uma atividade genérica.
     * 
     * @param codigo o código identificador da atividade
     * @param tempo o tempo gasto na atividade
     * @param tipo o tipo da atividade
     * @param creditos os créditos concedidos pela atividade
     * @param limiteCreditos o limite máximo de créditos computáveis
     * @param unidadeTempo a unidade de tempo utilizada (ex: hora(s), semestre(s))
     */
    public Atividade(String codigo, int tempo, String tipo, int creditos, int limiteCreditos, String unidadeTempo) {
        verificaEntrada(codigo); 
        verificaInteiro(tempo); 
        verificaTipo(tipo); 
        verificaInteiro(creditos); 
        verificaInteiro(limiteCreditos); 
        verificaEntrada(unidadeTempo);

        this.codigo = codigo;
        this.tempo = tempo;
        this.tipo = tipo;
        this.creditos = creditos;
        this.limiteCreditos = limiteCreditos;
        this.unidadeTempo = unidadeTempo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getLinkDocumentacao() {
        return linkDocumentacao;
    }

    public int getTempo() {
        return tempo;
    }

    public String getUnidadeTempo() {
        return unidadeTempo;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getLimiteCreditos() {
        return limiteCreditos;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * Define uma nova descrição para a atividade.
     *
     * @param descricao a nova descrição
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Define o link de documentação da atividade.
     *
     * @param linkDocumentacao o novo link
     */
    public void setLinkDocumentacao(String linkDocumentacao) {
        this.linkDocumentacao = linkDocumentacao;
    }

}
