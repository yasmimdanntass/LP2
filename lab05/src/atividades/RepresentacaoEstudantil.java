package atividades;

import static validadores.validador.verificaEntrada;

/**
 * Representa uma atividade de representação estudantil no sistema de atividades complementares.
 * 
 * Essa atividade é uma especialização da classe {@link Atividade} com um subtipo adicional
 * que descreve a natureza da representação (por exemplo, "Diretório Acadêmico", "Centro Acadêmico", etc).
 * 
 * O tipo da atividade é fixo como "REPRESENTACAO_ESTUDANTIL".
 * @author Yasmim Dantas
 */
public class RepresentacaoEstudantil extends Atividade {

    private String subtipo;

    /**
     * Constrói uma nova representação estudantil.
     *
     * @param codigo o código identificador da atividade
     * @param tempo o tempo de duração (em anos)
     * @param creditos os créditos que a atividade pode conceder
     * @param limiteCreditos o limite máximo de créditos que podem ser aproveitados
     * @param subtipo a descrição específica da representação (ex: "DCE", "DA", "Colegiado", etc)
     * 
     * @throws IllegalArgumentException se o subtipo for inválido (nulo ou vazio)
     */
    public RepresentacaoEstudantil(String codigo, int tempo, int creditos, int limiteCreditos, String subtipo) {
        super(codigo, tempo, "REPRESENTACAO_ESTUDANTIL", creditos, limiteCreditos, "ano(s)");
        verificaEntrada(subtipo);
        this.subtipo = subtipo;
    }

    public String getSubtipo() {
        return subtipo;
    }

}
