package elementos;

import static validadores.validador.*;

/**
 * Representa um elemento do tipo texto que pode ser associado a uma dica.
 * O texto possui uma pontuação que pode ser usada para calcular rankings ou avaliações.
 * 
 * Implementa a interface {@code Elemento}, oferecendo descrições resumida e detalhada do conteúdo textual.
 * 
 * @author Yasmim Dantas
 */
public class Texto implements Elemento {

    /** Conteúdo textual do elemento. */
    private String texto;

    /** Pontuação associada ao texto. */
    private int pontuacao;

    /**
     * Constrói um novo objeto {@code Texto} com base no conteúdo e pontuação fornecidos.
     *
     * @param texto o conteúdo textual
     * @param pontuacao a pontuação associada ao texto
     * @throws IllegalArgumentException se o texto for nulo/vazio ou a pontuação inválida
     */
	public Texto(String texto, int pontuacao) {
		verificaEntrada(texto); 
		verificaInteiro(pontuacao);
		this.texto = texto;
		this.pontuacao = pontuacao;
	}
	
	/**
     * Retorna a descrição resumida de texto.
     */
	@Override
	public String getDescricaoResumida() {
		return texto;
	}

	/**
     * Retorna a descrição com caracteres de texto.
     */
	@Override
	public String getDescricaoDetalhada() {
		return texto + " (" + texto.length() + " caractere(s))";
	}
	
	@Override
	public int getPontuacao() {
		return this.pontuacao;
	}

}
