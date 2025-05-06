package complementacao;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import atividades.Atividade;

import static validadores.validador.*;

/**
 * Representa um usuário do sistema, que pode criar dicas e adicionar atividades complementares.
 * @author Yasmim Dantas
 */
public class Usuario implements Comparable<Usuario> {

    private String nome;
    private String cpf;
    private String senha;
    private String matricula;
    private int pontosDicas;
    private int creditos;

    private Map<String, Atividade> atividades;
    private Map<String, String> historico;

    /**
     * Construtor do usuário.
     *
     * @param nome Nome do usuário.
     * @param cpf CPF do usuário (formato com 14 caracteres).
     * @param senha Senha do usuário (8 caracteres).
     * @param matricula Matrícula do aluno.
     */
    public Usuario(String nome, String cpf, String senha, String matricula) {
        verificaEntrada(nome); verificaEntrada(cpf); verificaEntrada(senha); verificaEntrada(matricula);

        if (senha.length() != 8) {
            throw new IllegalArgumentException("SENHA COM TAMANHO INVÁLIDO!");
        }

        if (cpf.length() != 14) {
            throw new IllegalArgumentException("CPF COM TAMANHO INVÁLIDO!");
        }

        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.matricula = matricula;
        this.atividades = new HashMap<>();
        this.historico = new HashMap<>();
    }

    /**
     * Altera a senha do usuário.
     *
     * @param senhaTentativa Senha atual.
     * @param senhaNova Nova senha.
     * @return true se a senha foi alterada com sucesso, false caso contrário.
     */
    public boolean alteraSenha(String senhaTentativa, String senhaNova) {
        verificaEntrada(senhaTentativa); verificaEntrada(senhaNova);

        if (!senhaTentativa.equals(senha) || senhaNova.length() != 8) {
            return false;
        }

        this.senha = senhaNova;
        return true;
    }

    /**
     * Aumenta os pontos de dica do usuário.
     *
     * @param qtdPontos Quantidade de pontos a somar.
     */
    public void aumentaPontosDicas(int qtdPontos) {
        if (qtdPontos < 0) {
            throw new IllegalArgumentException("QUANTIDADE DE PONTOS INVÁLIDA!");
        }
        this.pontosDicas += qtdPontos;
    }

    /**
     * Retorna a representação do usuário para o ranking.
     *
     * @return String com nome e pontuação.
     */
    public String exibeEstudanteRanking() {
        return String.format("Nome: %s | Pontuação total: %d", nome, pontosDicas);
    }

    /**
     * Exibe informações gerais do estudante.
     *
     * @return String com nome, matrícula e pontuação.
     */
    public String exibeEstudante() {
        return String.format("Nome: %s | Matricula: %s | Pontuação total: %d", nome, matricula, pontosDicas);
    }

    @Override
    public String toString() {
        return String.format("Nome: %s | CPF: %s | Matricula: %s | Pontuação total: %d", nome, cpf, matricula, pontosDicas);
    }

    /**
     * Adiciona uma atividade complementar ao usuário.
     *
     * @param nova Nova atividade.
     * @param codigo Código identificador da atividade.
     */
    public void adicionaAtividade(Atividade nova, String codigo) {
        int creditosAtuais = getCreditosAtividade(nova.getTipo());
        int limite = nova.getLimiteCreditos();

        if (creditosAtuais <= limite) {
            int creditosASomar = Math.min(nova.getCreditos(), limite - creditosAtuais);
            this.creditos += creditosASomar;
        }

        this.atividades.put(codigo, nova);
    }

    /**
     * Retorna a quantidade de créditos acumulados em um tipo de atividade.
     *
     * @param tipo Tipo da atividade.
     * @return Total de créditos considerados para o tipo.
     */
    public int getCreditosAtividade(String tipo) {
        int creditos = 0;
        int limiteCreditos = 0;

        for (Atividade a : atividades.values()) {
            if (a.getTipo().equals(tipo)) {
                creditos += a.getCreditos();
                limiteCreditos = a.getLimiteCreditos();
            }
        }

        if (creditos > limiteCreditos) creditos = limiteCreditos;

        return creditos;
    }

    /**
     * Gera um mapa com os créditos de cada tipo de atividade.
     *
     * @return String com o tipo e créditos acumulados.
     */
    public String geraMapaCreditosAtividades() {
        String[] possiveisAtividades = {"PESQUISA_EXTENSAO", "MONITORIA", "ESTAGIO", "REPRESENTACAO_ESTUDANTIL"};
        String retorno = "";

        for (String s : possiveisAtividades) {
            retorno += s + " - " + getCreditosAtividade(s) + "\n";
        }

        return retorno;
    }

    /**
     * Verifica se a meta de créditos foi alcançada.
     *
     * @return true se o usuário tem 22 ou mais créditos, false caso contrário.
     */
    public boolean verificaMetaAlcancada() {
        return (this.creditos >= 22);
    }

    /**
     * Altera a descrição de uma atividade cadastrada.
     *
     * @param codigoAtividade Código da atividade.
     * @param descricao Nova descrição.
     * @return true se a alteração foi feita, false se o código não existe.
     */
    public boolean alteraDescricaoAtividade(String codigoAtividade, String descricao) {
        if (!atividades.containsKey(codigoAtividade)) {
            return false;
        }
        Atividade a = atividades.get(codigoAtividade);
        a.setDescricao(descricao);
        return true;
    }

    /**
     * Altera a comprovação de uma atividade.
     *
     * @param codigoAtividade Código da atividade.
     * @param linkComprovacao Link da documentação comprobatória.
     * @return true se a alteração foi feita, false se o código não existe.
     */
    public boolean alteraComprovacaoAtividade(String codigoAtividade, String linkComprovacao) {
        if (!atividades.containsKey(codigoAtividade)) {
            return false;
        }
        Atividade a = atividades.get(codigoAtividade);
        a.setLinkDocumentacao(linkComprovacao);
        return true;
    }

    /**
     * Retorna um resumo da situação de créditos de um tipo específico de atividade.
     *
     * @param tipo Tipo da atividade.
     * @return String com créditos atuais e limite.
     */
    public String getRelatorioPorAtividade(String tipo) {
        String retorno = tipo + ": " + getCreditosAtividade(tipo) + "/";

        switch (tipo) {
            case "PESQUISA_EXTENSAO":
                retorno += 18;
                break;
            case "MONITORIA":
                retorno += 16;
                break;
            case "ESTAGIO":
                retorno += 18;
                break;
            default:
                retorno += 2;
        }

        return retorno;
    }

    /**
     * Adiciona um item ao histórico de relatórios.
     *
     * @param data Data do relatório.
     * @param relatorio Conteúdo do relatório.
     */
    public void adicionaRelatorio(String data, String relatorio) {
        historico.put(data, relatorio);
    }

    /**
     * Remove um item do histórico de relatórios.
     *
     * @param data Data do relatório a remover.
     * @return true se o item foi removido, false se não existia.
     */
    public boolean removeItemHistorico(String data) {
        if (!historico.containsKey(data)) {
            return false;
        }

        historico.remove(data);
        return true;
    }

    /**
     * Lista todo o histórico de relatórios do usuário.
     *
     * @return String com todas as datas e relatórios.
     */
    public String listaHistorico() {
        String retorno = "";

        for (String s : historico.keySet()) {
            retorno += s + "\n" + historico.get(s) + "\n";
        }

        return retorno;
    }

    /**
     * Verifica se a senha informada é a senha atual do usuário.
     *
     * @param senha Senha a ser verificada.
     * @return true se for igual à senha atual, false caso contrário.
     */
    public boolean verificaSenha(String senha) {
        return this.senha.equals(senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(cpf, other.cpf);
    }

    /**
     * Compara os nomes de dois usuários, para poder ordená-los.
     */
    @Override
    public int compareTo(Usuario o2) {
        return this.nome.compareToIgnoreCase(o2.getNome());
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getPontosDicas() {
        return pontosDicas;
    }

    public int getQtdAtividades() {
        return atividades.size();
    }

}
