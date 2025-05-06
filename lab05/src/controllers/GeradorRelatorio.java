package controllers;

import java.time.LocalDate;

import complementacao.Usuario;
import static validadores.validador.*;

/**
 * Classe responsável pela geração de relatórios relacionados às atividades complementares do usuário.
 * Pode gerar relatórios finais (completo ou por atividade) e parciais (com ou sem salvamento).
 * @author Yasmim Dantas
 */
public class GeradorRelatorio {

    /**
     * Construtor padrão do GeradorRelatorio.
     */
    public GeradorRelatorio() {}

    /**
     * Gera o relatório final completo de todas as atividades complementares do usuário.
     * Somente pode ser gerado se a meta de créditos tiver sido atingida.
     *
     * @param usuario Usuário para o qual o relatório será gerado.
     * @return String com o relatório completo, incluindo todas as atividades.
     * @throws IllegalArgumentException se o usuário for inválido ou não tiver atingido a meta.
     */
    public String geraRelatorioFinal(Usuario usuario) {
        verificaUsuario(usuario);

        if (!usuario.verificaMetaAlcancada()) {
            throw new IllegalArgumentException("O USUÁRIO NÃO ATINGIU A META DE CRÉDITOS!");
        }

        String[] tiposAtividades = {"PESQUISA_EXTENSAO", "MONITORIA", "ESTAGIO", "REPRESENTACAO_ESTUDANTIL"};
        String retorno = usuario.getNome() + ", " + usuario.getCpf() + ", " + usuario.getMatricula() + "\n";

        for (String s : tiposAtividades) {
            retorno += usuario.getRelatorioPorAtividade(s) + "\n";
        }

        return retorno + "TOTAL: 22";
    }

    /**
     * Gera o relatório final apenas de uma atividade específica, se a meta tiver sido alcançada.
     *
     * @param usuario Usuário para o qual o relatório será gerado.
     * @param tipo Tipo da atividade (ex: "PESQUISA_EXTENSAO").
     * @return String com o relatório final da atividade.
     * @throws IllegalArgumentException se o usuário for inválido, tipo inválido ou meta não atingida.
     */
    public String geraRelatorioFinalPorAtividade(Usuario usuario, String tipo) {
        verificaUsuario(usuario);
        verificaTipo(tipo);

        if (!usuario.verificaMetaAlcancada()) {
            throw new IllegalArgumentException("O USUÁRIO NÃO ATINGIU A META DE CRÉDITOS!");
        }

        String retorno = usuario.getNome() + ", " + usuario.getCpf() + ", " + usuario.getMatricula() + "\n";
        retorno += usuario.getRelatorioPorAtividade(tipo) + "\n";

        return retorno;
    }

    /**
     * Gera um relatório parcial com todas as atividades, podendo salvar no histórico do usuário.
     *
     * @param usuario Usuário para o qual o relatório será gerado.
     * @param salvar Se verdadeiro, o relatório será salvo no histórico com a data atual.
     * @return String com o relatório parcial.
     * @throws IllegalArgumentException se o usuário for inválido.
     */
    public String geraRelatorioParcial(Usuario usuario, boolean salvar) {
        verificaUsuario(usuario);

        String[] tiposAtividades = {"PESQUISA_EXTENSAO", "MONITORIA", "ESTAGIO", "REPRESENTACAO_ESTUDANTIL"};
        String retorno = usuario.getNome() + ", " + usuario.getCpf() + ", " + usuario.getMatricula() + "\n";

        for (String s : tiposAtividades) {
            retorno += usuario.getRelatorioPorAtividade(s) + "\n";
        }

        LocalDate data = LocalDate.now();

        if (salvar) {
            usuario.adicionaRelatorio(data.toString(), retorno);
        }

        return retorno;
    }

    /**
     * Gera um relatório parcial apenas de uma atividade específica, com opção de salvamento.
     *
     * @param usuario Usuário para o qual o relatório será gerado.
     * @param tipo Tipo da atividade (ex: "MONITORIA").
     * @param salvar Se verdadeiro, o relatório será salvo no histórico com a data atual.
     * @return String com o relatório parcial da atividade.
     * @throws IllegalArgumentException se o usuário ou tipo forem inválidos.
     */
    public String geraRelatorioParcialPorAtividade(Usuario usuario, String tipo, boolean salvar) {
        verificaUsuario(usuario);
        verificaTipo(tipo);

        String retorno = usuario.getNome() + ", " + usuario.getCpf() + ", " + usuario.getMatricula() + "\n";
        retorno += usuario.getRelatorioPorAtividade(tipo) + "\n";

        LocalDate data = LocalDate.now();

        if (salvar) {
            usuario.adicionaRelatorio(data.toString(), retorno);
        }

        return retorno;
    }
}

