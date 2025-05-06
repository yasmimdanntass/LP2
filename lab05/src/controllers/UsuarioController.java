package controllers;

import java.util.*;
import comparator.ComparatorUsuarioDicas;
import static validadores.validador.*;
import complementacao.Usuario;

/**
 * Controller responsável por gerenciar os usuários (estudantes) do sistema.
 * @author Yasmim Dantas
 */
public class UsuarioController {

    private Map<String, Usuario> usuarios;

    /**
     * Construtor do controller. Inicializa o mapa de usuários.
     */
    public UsuarioController() {
        usuarios = new HashMap<String, Usuario>();
    }

    /**
     * Cria um novo estudante com os dados informados.
     *
     * @param nome Nome do estudante.
     * @param cpf CPF do estudante (14 caracteres).
     * @param senha Senha do estudante (8 caracteres).
     * @param matricula Matrícula do estudante.
     * @return true se o estudante foi criado com sucesso, false se já existe.
     */
    public boolean criaEstudante(String nome, String cpf, String senha, String matricula) {
        verificaEntrada(nome); verificaEntrada(cpf); verificaEntrada(senha); verificaEntrada(matricula);

        if (senha.length() != 8) {
            throw new IllegalArgumentException("SENHA COM TAMANHO INVÁLIDO!");
        }

        if (cpf.length() != 14) {
            throw new IllegalArgumentException("CPF COM TAMANHO INVÁLIDO!");
        }

        if (verificaEstudante(cpf)) {
            return false;
        }

        Usuario novo = new Usuario(nome, cpf, senha, matricula);
        usuarios.put(cpf, novo);
        return true;
    }

    /**
     * Lista os estudantes cadastrados, ordenados pelo nome.
     *
     * @return Array com a representação dos estudantes ordenados pelo nome.
     */
    public String[] listaUsuariosPorNome() {
        List<String> retorno = new ArrayList<String>();

        for (Usuario u : usuarios.values()) {
            retorno.add(u.exibeEstudante());
        }

        Collections.sort(retorno);
        return retorno.toArray(new String[0]);
    }

    /**
     * Altera a senha de um estudante, caso a senha antiga esteja correta.
     *
     * @param cpf CPF do estudante.
     * @param senhaAntiga Senha atual.
     * @param novaSenha Nova senha (8 caracteres).
     * @return true se a senha foi alterada com sucesso, false caso contrário.
     */
    public boolean alteraSenhaEstudante(String cpf, String senhaAntiga, String novaSenha) {
        verificaEntrada(cpf); verificaEntrada(senhaAntiga); verificaEntrada(novaSenha);

        if (novaSenha.length() != 8) {
            throw new IllegalArgumentException("SENHA COM TAMANHO INVÁLIDO!");
        }

        if (!verificaEstudante(cpf)) {
            return false;
        }

        return usuarios.get(cpf).alteraSenha(senhaAntiga, novaSenha);
    }

    /**
     * Adiciona pontos de dicas para um estudante.
     *
     * @param cpf CPF do estudante.
     * @param qtdPontos Quantidade de pontos a serem adicionados.
     * @return true se os pontos foram adicionados com sucesso, false caso o estudante não exista.
     */
    public boolean adicionaPontosDica(String cpf, int qtdPontos) {
        verificaEntrada(cpf); verificaInteiro(qtdPontos);

        if (!verificaEstudante(cpf)) {
            return false;
        }

        usuarios.get(cpf).aumentaPontosDicas(qtdPontos);
        return true;
    }

    /**
     * Lista os estudantes em ordem de pontuação de dicas.
     *
     * @return Array com o ranking dos estudantes por pontos de dicas.
     */
    public String[] listaUsuariosRankingDicas() {
        List<Usuario> ranking = new ArrayList<Usuario>(usuarios.values());

        Collections.sort(ranking, new ComparatorUsuarioDicas());

        String[] retorno = new String[ranking.size()];

        for (int i = 0; i < ranking.size(); i++) {
            retorno[i] = (i + 1) + ". - " + ranking.get(i).exibeEstudanteRanking();
        }

        return retorno;
    }

    /**
     * Verifica se a senha informada está correta para o CPF dado.
     *
     * @param cpf CPF do estudante.
     * @param senha Senha a ser verificada.
     * @return true se a senha estiver correta, false caso contrário.
     */
    public boolean verificaSenhaUsuario(String cpf, String senha) {
        verificaEntrada(cpf); verificaEntrada(senha);

        if (!verificaEstudante(cpf)) {
            throw new IllegalArgumentException("CPF NÃO CONSTA NO SISTEMA!");
        }

        return usuarios.get(cpf).verificaSenha(senha);
    }

    /**
     * Retorna o usuário correspondente ao CPF e senha informados.
     *
     * @param cpf CPF do estudante.
     * @param senha Senha do estudante.
     * @return O objeto Usuario correspondente.
     */
    public Usuario getUsuario(String cpf, String senha) {
        verificaEntrada(cpf); verificaEntrada(senha);

        if (!verificaEstudante(cpf)) {
            throw new IllegalArgumentException("CPF NÃO CONSTA NO SISTEMA!");
        }

        if (!verificaSenhaUsuario(cpf, senha)) {
            throw new IllegalArgumentException("A SENHA ESTÁ ERRADA!");
        }

        return usuarios.get(cpf);
    }

    /**
     * Lista o histórico de atividades do estudante.
     *
     * @param cpf CPF do estudante.
     * @param senha Senha do estudante.
     * @return String com o histórico formatado.
     */
    public String listaHistorico(String cpf, String senha) {
        verificaEntrada(cpf); verificaEntrada(senha);

        Usuario u = getUsuario(cpf, senha);
        return u.listaHistorico();
    }

    /**
     * Remove um item do histórico do estudante com base na data.
     *
     * @param cpf CPF do estudante.
     * @param senha Senha do estudante.
     * @param data Data do item a ser removido.
     * @return true se o item foi removido com sucesso, false caso contrário.
     */
    public boolean excluiItemHistorico(String cpf, String senha, String data) {
        verificaEntrada(cpf); verificaEntrada(senha); verificaEntrada(data);

        Usuario u = getUsuario(cpf, senha);
        return u.removeItemHistorico(data);
    }

    /**
     * Verifica se existe um estudante cadastrado com o CPF informado.
     *
     * @param cpf CPF a ser verificado.
     * @return true se o estudante existir, false caso contrário.
     */
    private boolean verificaEstudante(String cpf) {
        return usuarios.containsKey(cpf);
    }
}
