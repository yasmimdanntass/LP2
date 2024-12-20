package br.edu.ufcg.computacao.p2lp2.coisa;

/**
* Representação do registro de tempo de estudos online no sistema. Cada disciplina é identificada pelo nome, tendo como "padrão" o tempo esperado de 120 horas.
* 
* @author Yasmim Dantas da Costa Souza
*/
public class RegistroTempoOnline {
	
	private String nomeDisciplina;
	private int tempoOnlineEsperado;
	private int tempo = 0;
	
	/**
	 * Constrói o registro de tempo online a partir do nome da disciplina, com o tempo padrão sendo 120 horas.
	 * 
	 * @param nomeDisciplina o nome da disciplina
	 */
	public RegistroTempoOnline(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
		this.tempoOnlineEsperado = 120;
	}
	
	/**
	 * Constrói o registro de tempo online a partir do nome da disciplina e do tempo esperado de estudos para ela.
	 * 
	 * @param nomeDisciplina o nome da disciplina.
	 * @param tempoOnlineEsperado o tempo esperado de estudos online para a disciplina.
	 */
	public RegistroTempoOnline(String nomeDisciplina, int tempoOnlineEsperado) {
		this.nomeDisciplina = nomeDisciplina;
		this.tempoOnlineEsperado = tempoOnlineEsperado;
	}
	
	/**
	 * Adiciona tempo online estudado para a disciplina.
	 * 
	 * @param tempo tempo online estudado para a disciplina
	 */
	public void adicionaTempoOnline(int tempo) {
		this.tempo += tempo;
		
	}
	
	/**
	 * Verifica se a meta de tempo estudado online foi atingida (ou seja, se é igual ou superior ao tempoOnlineEsperado.)
	 * 
	 * @return true, se o tempo estudado for igual ou maior que o esperado, e false caso contrário.
	 */
	public boolean atingiuMetaTempoOnline() {
		return (tempo >= tempoOnlineEsperado);
		
	}
	
	/**
	 * Retorna a string formatada de informações sobre a disciplina.
	 * 
	 * @return retorna uma string com o nome da disciplina, tempo total estudado online e o tempo esperado.
	 */
	@Override
	public String toString() {
		return String.format("%s %d/%d", nomeDisciplina, tempo, tempoOnlineEsperado);
		
	}
	
}
