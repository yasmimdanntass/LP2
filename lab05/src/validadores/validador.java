package validadores;

import complementacao.Usuario;

/**
 * Classe utilitária para validações comuns no sistema.
 * Fornece métodos estáticos para validar entradas de texto, inteiros,
 * objetos do tipo {@link Usuario}, e tipos de atividades permitidos.
 * 
 * 
 * @author Yasmim Dantas
 */
public class validador {

    /**
     * Verifica se a entrada de texto é nula ou vazia.
     *
     * @param entrada a string a ser verificada
     * @throws NullPointerException se a entrada for {@code null}
     * @throws IllegalArgumentException se a entrada for vazia ou composta apenas por espaços
     */
	public static void verificaEntrada(String entrada) {
		if (entrada == null) {
			throw new NullPointerException("ENTRADA NULA!");
		} else if (entrada.isBlank()) {
			throw new IllegalArgumentException("ENTRADA VAZIA!");
		}		
	}
	
    /**
     * Verifica se o objeto {@code Usuario} é nulo.
     *
     * @param usuario o objeto {@code Usuario} a ser verificado
     * @throws NullPointerException se o usuário for {@code null}
     */
	public static void verificaUsuario(Usuario usuario) {
		if (usuario == null) {
			throw new NullPointerException("OBJETO NULO!");
		}
	}

    /**
     * Verifica se um número inteiro é negativo.
     *
     * @param entrada o valor inteiro a ser verificado
     * @throws IllegalArgumentException se o valor for menor que zero
     */
	public static void verificaInteiro(int entrada) {
		if (entrada < 0) {
			throw new IllegalArgumentException("INTEIRO MENOR QUE 0!");
		}
	}

    /**
     * Verifica se o tipo de atividade informado é válido.
     * @param tipo o tipo de atividade
     * @throws IllegalArgumentException se o tipo for inválido
     */
	public static void verificaTipo(String tipo) {
		verificaEntrada(tipo);
		
		if (!(tipo.equals("ESTAGIO")) && 
		    !(tipo.equals("MONITORIA")) && 
		    !(tipo.equals("PESQUISA_EXTENSAO")) && 
		    !(tipo.equals("REPRESENTACAO_ESTUDANTIL"))) {
			throw new IllegalArgumentException("TIPO INVÁLIDO!");
		}
	}
}
