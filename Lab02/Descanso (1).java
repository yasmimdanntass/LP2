package br.edu.ufcg.computacao.p2lp2.coisa;
/**
* Representação do descanso de cada aluno de acordo com o total de horas por semana.
* 
* @author Yasmim Dantas da Costa Souza
*/

public class Descanso {
	
	private int horasDescanso;
	private int numeroSemanas;
	private String estado;
	private String emoji;
	
	/**
	* Constrói o descanso e define o padrão das horas de descanso como 0 e do número de semanas como 1.
	*/
	public Descanso() {
		this.horasDescanso = 0;
		this.numeroSemanas = 1;
		this.estado = "cansado";
		this.emoji = "";
	}
	/**
	* Recebe as horas de descanso.
	* 
	* @param horasDescanso: horas totais de descanso
	*/
	public void defineHorasDescanso(int horasDescanso) {
		this.horasDescanso = horasDescanso;
	}
	/**
	 * Recebe o total de semanas
	 * 
	 * @param numeroSemanas: número total de semanas
	 */
	public void defineNumeroSemanas(int numeroSemanas) { 
		this.numeroSemanas = numeroSemanas;
	}
	/**
	 * Define o emoji do estado do aluno.
	 * 
	 * @param emoji: string com o emoji.
	 */
	public void definirEmoji(String emoji) {
		this.emoji = emoji;
	}
	
	/**
	 * Calcula o status geral do aluno com base na quantidade média de horas de descanso por semana.
     * O aluno será considerado "descansado" se descansar, em média, 26 horas ou mais por semana.
     * Caso contrário, será considerado "cansado". O emoji será retornado apenas se for declarado pelo método "definirEmoji", e,
     * caso haja uma alteração no estado do indivíduo, o emoji será redefinido para "".
	 */
	
	public void calculaEstado() {
		double descansoTotal = this.horasDescanso / this.numeroSemanas;
		String novoEstado;
		
		if (descansoTotal >= 26) {
			novoEstado = "descansado";
		} else {
			novoEstado = "cansado";
		}
		
		if (!this.estado.equals(novoEstado)) {
	        this.emoji = ""; 
	    }
		
		this.estado = novoEstado;
	}
	/**
     * Retorna o status geral do aluno, calculado no método "calculaEstado()".
     * 
     * @return estado: "descansado" se a média de horas por semana for maior ou igual a 26, caso contrário, retorna "cansado". Também retorna o emoji,
     * caso esse não seja uma string vazia.
     */
	public String getStatusGeral() {
		
		calculaEstado(); // verifica se o estado é "cansado" ou "descansado"
		
		if (this.emoji != null && !this.emoji.isEmpty()) {
	        return this.estado + " - " + this.emoji;
	    }
		
		return this.estado;
	}
	
	
}
