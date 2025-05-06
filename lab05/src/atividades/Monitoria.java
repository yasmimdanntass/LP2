package atividades;

import static validadores.validador.verificaEntrada;

/**
 * Representa uma atividade de monitoria.
 * 
 * Cada monitoria está associada a uma disciplina específica, com tempo medido em semestres.
 * @author Yasmim Dantas
 */
public class Monitoria extends Atividade {

    private String disciplina;

    /**
     * Constrói uma nova monitoria.
     *
     * @param codigo o código identificador da atividade
     * @param tempo o tempo da atividade em semestres
     * @param creditos os créditos concedidos pela atividade
     * @param limiteCreditos o limite máximo de créditos que podem ser computados
     * @param disciplina o nome da disciplina associada à monitoria
     */
    public Monitoria(String codigo, int tempo, int creditos, int limiteCreditos, String disciplina) {
        super(codigo, tempo, "MONITORIA", creditos, limiteCreditos, "semestre(s)");
        verificaEntrada(disciplina);
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return disciplina;
    }

}
