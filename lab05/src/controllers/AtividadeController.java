package controllers;

import static validadores.validador.*;
import complementacao.Usuario;
import atividades.Atividade;
import atividades.Estagio;
import atividades.Monitoria;
import atividades.PesquisaExtensao;
import atividades.RepresentacaoEstudantil;

/**
 * Controlador responsável por criar e gerenciar atividades de um usuário.
 * @author Yasmim Dantas
 */
public class AtividadeController {

    /**
     * Construtor padrão do AtividadeController.
     */
    public AtividadeController() {
    }

    /**
     * Cria uma atividade de Pesquisa ou Extensão para um usuário.
     *
     * @param usuario  Usuário que irá receber a atividade.
     * @param tempo    Tempo dedicado à atividade (em horas).
     * @param subtipo  Subtipo da atividade (ex: "projeto", "extensão").
     * @return Código da atividade criada.
     * @throws IllegalArgumentException se algum parâmetro for inválido.
     */
    public String criaAtividadePesquisaExtensaoEmUsuario(Usuario usuario, int tempo, String subtipo) {
        verificaUsuario(usuario);
        verificaInteiro(tempo);
        
        if (subtipo == null || subtipo.isBlank()) {
        	return "ATIVIDADE NÃO CADASTRADA"; 
        }

        String codigoAtividade = usuario.getCpf() + "_" + (usuario.getQtdAtividades()+1);
        int creditos = Math.floorDiv(tempo, 12);
        if (creditos > 18) {
            creditos = 18;
        }

        Atividade nova = new PesquisaExtensao(codigoAtividade, tempo, creditos, 18, subtipo);
        usuario.adicionaAtividade(nova, codigoAtividade);
        return codigoAtividade;
    }

    /**
     * Cria uma atividade de Monitoria para um usuário.
     *
     * @param usuario    Usuário que irá receber a atividade.
     * @param tempo      Tempo dedicado à atividade (em horas).
     * @param disciplina Disciplina da monitoria.
     * @return Código da atividade criada.
     * @throws IllegalArgumentException se algum parâmetro for inválido.
     */
    public String criaAtividadeMonitoriaEmUsuario(Usuario usuario, int tempo, String disciplina) {
        verificaUsuario(usuario);
        verificaInteiro(tempo);
        verificaEntrada(disciplina);

        String codigoAtividade = usuario.getCpf() + "_" + (usuario.getQtdAtividades() + 1);
        int creditos = 4 * tempo;
        if (creditos > 16) {
            creditos = 16;
        }

        Atividade nova = new Monitoria(codigoAtividade, tempo, creditos, 16, disciplina);
        usuario.adicionaAtividade(nova, codigoAtividade);
        return codigoAtividade;
    }

    /**
     * Cria uma atividade de Estágio para um usuário.
     *
     * @param usuario Usuário que irá receber a atividade.
     * @param tempo   Tempo dedicado à atividade (em horas).
     * @param empresa Nome da empresa do estágio.
     * @return Código da atividade criada.
     * @throws IllegalArgumentException se tempo < 300 ou se os parâmetros forem inválidos.
     */
    public String criaAtividadeEstagioEmUsuario(Usuario usuario, int tempo, String empresa) {
        verificaUsuario(usuario);
        verificaInteiro(tempo);
        verificaEntrada(empresa);

        if (tempo < 300) {
            return "ATIVIDADE NÃO CADASTRADA";
        }

        String codigoAtividade = usuario.getCpf() + "_" + (usuario.getQtdAtividades() + 1);
        int creditos = Math.floorDiv(tempo, 60);
        if (creditos > 18) {
            creditos = 18;
        }

        Atividade nova = new Estagio(codigoAtividade, tempo, creditos, 18, empresa);
        usuario.adicionaAtividade(nova, codigoAtividade);
        return codigoAtividade;
    }

    /**
     * Cria uma atividade de Representação Estudantil para um usuário.
     *
     * @param usuario Usuário que irá receber a atividade.
     * @param tempo   Tempo dedicado à atividade (em horas).
     * @param subtipo Tipo da representação (ex: "DA", "DCE").
     * @return Código da atividade criada.
     * @throws IllegalArgumentException se tempo < 1 ou se os parâmetros forem inválidos.
     */
    public String criaAtividadeRepresentacaoEstudantilEmUsuario(Usuario usuario, int tempo, String subtipo) {
        verificaUsuario(usuario);
        verificaInteiro(tempo);
        
        if (subtipo == null || subtipo.isBlank()) {
        	return "ATIVIDADE NÃO CADASTRADA"; 
        }

        if (tempo < 1) {
            throw new IllegalArgumentException("REPRESENTAÇÃO ESTUDANTIL COM MENOS TEMPO QUE O MÍNIMO NECESSÁRIO!");
        }

        String codigoAtividade = usuario.getCpf() + "_" + (usuario.getQtdAtividades() + 1);
        int creditos = 2 * tempo;
        if (creditos > 2) {
            creditos = 2;
        }

        Atividade nova = new RepresentacaoEstudantil(codigoAtividade, tempo, creditos, 2, subtipo);
        usuario.adicionaAtividade(nova, codigoAtividade);
        return codigoAtividade;
    }

    /**
     * Retorna a quantidade de créditos de um tipo específico de atividade do usuário.
     *
     * @param usuario Usuário dono das atividades.
     * @param tipo    Tipo da atividade.
     * @return Número de créditos acumulados para o tipo.
     * @throws IllegalArgumentException se o tipo for inválido.
     */
    public int creditosAtividade(Usuario usuario, String tipo) {
        verificaUsuario(usuario);
        verificaTipo(tipo);
        return usuario.getCreditosAtividade(tipo);
    }

    /**
     * Gera uma representação dos créditos acumulados por tipo de atividade.
     *
     * @param usuario Usuário a ser consultado.
     * @return Mapa de créditos no formato de String.
     */
    public String geraMapaCreditosAtividades(Usuario usuario) {
        verificaUsuario(usuario);
        return usuario.geraMapaCreditosAtividades();
    }

    /**
     * Verifica se o usuário atingiu a meta mínima de créditos.
     *
     * @param usuario Usuário a ser verificado.
     * @return true se a meta foi alcançada, false caso contrário.
     */
    public boolean verificaMetaAlcancada(Usuario usuario) {
        verificaUsuario(usuario);
        return usuario.verificaMetaAlcancada();
    }

    /**
     * Altera a descrição de uma atividade existente do usuário.
     *
     * @param usuario         Usuário dono da atividade.
     * @param codigoAtividade Código identificador da atividade.
     * @param descricao       Nova descrição da atividade.
     * @return true se a alteração foi feita com sucesso, false caso contrário.
     */
    public boolean alteraDescricaoAtividade(Usuario usuario, String codigoAtividade, String descricao) {
        verificaUsuario(usuario);
        verificaEntrada(codigoAtividade);
        verificaEntrada(descricao);
        return usuario.alteraDescricaoAtividade(codigoAtividade, descricao);
    }

    /**
     * Altera a comprovação (link) de uma atividade do usuário.
     *
     * @param usuario         Usuário dono da atividade.
     * @param codigoAtividade Código identificador da atividade.
     * @param linkDescricao   Novo link de comprovação da atividade.
     * @return true se a alteração foi feita com sucesso, false caso contrário.
     */
    public boolean alteraComprovacaoAtividade(Usuario usuario, String codigoAtividade, String linkDescricao) {
        verificaUsuario(usuario);
        verificaEntrada(codigoAtividade);
        verificaEntrada(linkDescricao);
        return usuario.alteraComprovacaoAtividade(codigoAtividade, linkDescricao);
    }
}
