package br.edu.ufcg.computacao.p2lp2.coisa;
/**
* Representação de um resumo, contendo tema e conteúdo como atributos.
* 
* @author Yasmim Dantas da Costa Souza
*/
public class Resumos {
	
	private String tema;
	private String conteudo;	
	
	/**
	 * Constrói o resumo a partir do tema e conteúdo.
	 * @param tema
	 * @param conteudo
	 */
	public Resumos(String tema, String conteudo) {
		this.tema = tema;
		this.conteudo = conteudo;
		
	}
	
	/**
	 * Retorna o tema do resumo.
	 * @return o tema.
	 */
	public String getTema() {
		return this.tema;
	}
	
	/**
	 * Retorna o conteúdo do resumo.
	 * @return o conteúdo.
	 */
	public String getConteudo() {
		return this.conteudo;
	}
	
	/**
	 * Retorna uma string formatada contendo o tema e o conteúdo separados por dois pontos.
	 * @return string 	 */
	@Override
	public String toString() {
		return this.tema + ": " + this.conteudo;
	}
	
	
	
}
