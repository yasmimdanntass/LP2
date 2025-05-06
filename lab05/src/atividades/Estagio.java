package atividades;

import static validadores.validador.verificaEntrada;

/**
 * Representa uma atividade de estágio.
 * 
 * Cada estágio está associado a uma empresa específica, com tempo medido em horas.
 * @author Yasmim Dantas
 */
public class Estagio extends Atividade {

    private String empresa;

    /**
     * Constrói um novo estágio.
     *
     * @param codigo o código identificador da atividade
     * @param tempo o tempo da atividade em horas
     * @param creditos os créditos concedidos pela atividade
     * @param limiteCreditos o limite máximo de créditos que podem ser computados
     * @param empresa o nome da empresa onde o estágio foi realizado
     */
    public Estagio(String codigo, int tempo, int creditos, int limiteCreditos, String empresa) {
        super(codigo, tempo, "ESTAGIO", creditos, limiteCreditos, "hora(s)");
        verificaEntrada(empresa);
        this.empresa = empresa;
    }

    public String getEmpresa() {
        return empresa;
    }

}
