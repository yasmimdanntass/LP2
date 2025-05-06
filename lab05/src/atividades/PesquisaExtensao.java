package atividades;

import static validadores.validador.verificaEntrada;

/**
 * Representa uma atividade de pesquisa ou extensão no sistema de atividades complementares.
 * 
 * Essa atividade é uma especialização da classe {@link Atividade} com um subtipo adicional
 * que especifica o tipo da atividade (por exemplo, "Projeto de Iniciação Científica", "Extensão Universitária", etc).
 * 
 * O tipo da atividade é fixo como "PESQUISA_EXTENSAO", e o tempo é contado em meses.
 * 
 * @author Yasmim Dantas
 */
public class PesquisaExtensao extends Atividade {
    
    private String subtipo;

    /**
     * Constrói uma nova atividade do tipo pesquisa ou extensão.
     *
     * @param codigo o código identificador da atividade
     * @param tempo o tempo de duração da atividade (em meses)
     * @param creditos os créditos concedidos por essa atividade
     * @param limiteCreditos o limite máximo de créditos aceitos para esse tipo de atividade
     * @param subtipo a descrição específica da atividade (ex: "Iniciação Científica", "Extensão", etc)
     * 
     * @throws IllegalArgumentException se o subtipo for inválido (nulo ou vazio)
     */
    public PesquisaExtensao(String codigo, int tempo, int creditos, int limiteCreditos, String subtipo) {
        super(codigo, tempo, "PESQUISA_EXTENSAO", creditos, limiteCreditos, "mes(es)");
        verificaEntrada(subtipo);
        this.subtipo = subtipo;
    }

    public String getSubtipo() {
        return subtipo;
    }   
    
}
