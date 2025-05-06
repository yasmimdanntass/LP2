package controllers;

import java.util.*;

import complementacao.Dica;
import complementacao.Usuario;
import static validadores.validador.*;

/**
 * Classe responsável por gerenciar dicas criadas pelos usuários.
 * Permite adicionar dicas, inserir elementos (texto, multimídia, referência),
 * listar dicas de forma resumida ou detalhada e controlar a pontuação dos usuários.
 * @author Yasmim Dantas
 */
public class DicaController {

    private List<Dica> dicas;

    /**
     * Construtor padrão. Inicializa a lista de dicas.
     */
    public DicaController() {
        dicas = new ArrayList<Dica>();
    }

    /**
     * Adiciona uma nova dica com o tema informado.
     *
     * @param usuario Usuário que está criando a dica.
     * @param tema Tema da dica.
     * @return Posição (índice) da dica na lista.
     * @throws IllegalArgumentException se o usuário ou tema forem inválidos.
     */
    public int adicionaDica(Usuario usuario, String tema) {
        verificaUsuario(usuario);
        verificaEntrada(tema);

        Dica nova = new Dica(usuario, tema);
        dicas.add(nova);
        return dicas.size() - 1;
    }

    /**
     * Adiciona um elemento de texto a uma dica existente.
     *
     * @param usuario Usuário que está adicionando o elemento.
     * @param posicaoDica Posição da dica na lista.
     * @param texto Conteúdo textual a ser adicionado.
     * @return true se o elemento foi adicionado com sucesso.
     * @throws IllegalArgumentException se a entrada for inválida, a posição inexistente ou o usuário não for o autor.
     */
    public boolean adicionaElementoTextoDica(Usuario usuario, int posicaoDica, String texto) {
        verificaUsuario(usuario);
        verificaEntrada(texto);

        if (!(posicaoDica < dicas.size() && posicaoDica >= 0)) {
            throw new IllegalArgumentException("POSIÇÃO NÃO PREENCHIDA OU NÃO EXISTENTE!");
        }

        Dica d = dicas.get(posicaoDica);

        if (!d.getUsuario().equals(usuario)) {
            throw new IllegalArgumentException("O USUÁRIO NÃO É AUTOR DESSA DICA!");
        }

        int pontuacao = 0;

        if (texto.length() >= 100) {
            pontuacao = (int) Math.floor(texto.length() / 10);
        }

        if (pontuacao > 50) {
            pontuacao = 50;
        }

        d.adicionaElementoTexto(texto, pontuacao);
        usuario.aumentaPontosDicas(pontuacao);
        return true;
    }

    /**
     * Adiciona um elemento multimídia a uma dica existente.
     *
     * @param usuario Usuário que está adicionando o elemento.
     * @param posicaoDica Posição da dica na lista.
     * @param link Link do conteúdo multimídia.
     * @param cabecalho Cabeçalho descritivo do conteúdo.
     * @param tempo Tempo de duração (em minutos).
     * @return true se o elemento foi adicionado com sucesso.
     * @throws IllegalArgumentException se algum dado for inválido ou o usuário não for o autor.
     */
    public boolean adicionaElementoMultimidiaDica(Usuario usuario, int posicaoDica, String link, String cabecalho, int tempo) {
        verificaUsuario(usuario);
        verificaEntrada(link);
        verificaEntrada(cabecalho);
        verificaInteiro(tempo);

        if (!(posicaoDica < dicas.size() && posicaoDica >= 0)) {
            throw new IllegalArgumentException("POSIÇÃO NÃO PREENCHIDA OU NÃO EXISTENTE!");
        }

        Dica d = dicas.get(posicaoDica);

        if (!d.getUsuario().equals(usuario)) {
            throw new IllegalArgumentException("O USUÁRIO NÃO É AUTOR DESSA DICA!");
        }

        int pontuacao = (int) Math.floor(tempo / 5);

        if (pontuacao > 50) {
            pontuacao = 50;
        }

        d.adicionaElementoMultimidia(link, cabecalho, tempo, pontuacao);
        usuario.aumentaPontosDicas(pontuacao);
        return true;
    }

    /**
     * Adiciona uma referência bibliográfica a uma dica existente.
     *
     * @param usuario Usuário que está adicionando o elemento.
     * @param posicaoDica Posição da dica na lista.
     * @param titulo Título da referência.
     * @param fonte Fonte da referência.
     * @param ano Ano da publicação.
     * @param conferida Indica se a fonte foi verificada.
     * @param relevancia Nível de relevância (1 a 5).
     * @return true se a referência foi adicionada com sucesso.
     * @throws IllegalArgumentException se algum parâmetro for inválido ou o usuário não for o autor.
     */
    public boolean adicionaElementoReferenciaDica(Usuario usuario, int posicaoDica, String titulo, String fonte, int ano, boolean conferida, int relevancia) {
        verificaUsuario(usuario);
        verificaEntrada(titulo);
        verificaEntrada(fonte);
        verificaInteiro(ano);

        if (relevancia < 1 || relevancia > 5) {
            throw new IllegalArgumentException("RELEVÂNCIA FORA DO INTERVALO PERMITIDO!");
        }

        if (!(posicaoDica < dicas.size() && posicaoDica >= 0)) {
            throw new IllegalArgumentException("POSIÇÃO NÃO PREENCHIDA OU NÃO EXISTENTE!");
        }

        Dica d = dicas.get(posicaoDica);

        if (!d.getUsuario().equals(usuario)) {
            throw new IllegalArgumentException("O USUÁRIO NÃO É AUTOR DESSA DICA!");
        }

        int pontuacao = conferida ? 15 : 0;

        d.adicionaElementoReferencia(titulo, fonte, fonte, ano, conferida, relevancia, pontuacao);
        usuario.aumentaPontosDicas(pontuacao);

        return true;
    }

    /**
     * Retorna todas as dicas cadastradas em formato resumido.
     *
     * @return Array com as descrições resumidas das dicas.
     */
    public String[] listaDicas() {
        String[] retorno = new String[dicas.size()];

        for (int i = 0; i < dicas.size(); i++) {
            retorno[i] = dicas.get(i).getDicaResumida();
        }

        return retorno;
    }

    /**
     * Retorna todas as dicas cadastradas em formato detalhado.
     *
     * @return Array com as descrições detalhadas das dicas.
     */
    public String[] listaDicasDetalhes() {
        String[] retorno = new String[dicas.size()];

        for (int i = 0; i < dicas.size(); i++) {
            retorno[i] = dicas.get(i).getDicaDetalhada();
        }

        return retorno;
    }

    /**
     * Retorna uma dica específica em formato resumido.
     *
     * @param posicaoDica Posição da dica na lista.
     * @return Descrição resumida da dica.
     * @throws IllegalArgumentException se a posição for inválida.
     */
    public String listaDica(int posicaoDica) {
        if (!(posicaoDica < dicas.size() && posicaoDica >= 0)) {
            throw new IllegalArgumentException("POSIÇÃO NÃO PREENCHIDA OU NÃO EXISTENTE!");
        }

        return dicas.get(posicaoDica).getDicaResumida();
    }

    /**
     * Retorna uma dica específica em formato detalhado.
     *
     * @param posicaoDica Posição da dica na lista.
     * @return Descrição detalhada da dica.
     * @throws IllegalArgumentException se a posição for inválida.
     */
    public String listaDicaDetalhes(int posicaoDica) {
        if (!(posicaoDica < dicas.size() && posicaoDica >= 0)) {
            throw new IllegalArgumentException("POSIÇÃO NÃO PREENCHIDA OU NÃO EXISTENTE!");
        }
        return dicas.get(posicaoDica).getDicaDetalhada();
    }
}
