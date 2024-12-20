package br.edu.ufcg.computacao.p2lp2.coisa;
import java.util.*;
/**
* Representação de cada uma das disciplinas do aluno. Cada disciplina é identificada por seu nome, possuindo características próprias além dessa.
* 
* @author Yasmim Dantas da Costa Souza
*/
public class Disciplina {
	/**
	 * Nome da disciplina.
	 */
	private String nomeDisciplina;
	/**
	 * Horas de estudo.
	 */
	private int horas = 0;
	/**
	 * Quantidade de notas da disciplina
	 */
	private int qtdNotas;
	/**
	 * Array para representar as notas possíveis.
	 */
	private double[] notas;
	/**
	 * Array para representar os pesos possíveis.
	 */
	private int[] pesos;
	/**
	 * A média do aluno.
	 */
	private double media;
	/**
	 * Constrói a Disciplina a partir do nome, com quantidade de notas 4 como padrao e pesos iguais a 1.
	 * @param nomeDisciplina nome da disciplina.
	 */
	public Disciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;	
		this.qtdNotas = 4;
		this.notas = new double[this.qtdNotas];
		this.pesos = new int[this.qtdNotas];
		Arrays.fill(this.pesos, 1);
;	}
	/**
	 * Constrói a Disciplina a partir do nome e qtd. de notas (Parte 5.1 do Lab02, bônus).
	 * @param nomeDisciplina nome da disciplina
	 * @param qtdNotas quantidade de notas
	 */
	public Disciplina(String nomeDisciplina, int qtdNotas) {
		this.nomeDisciplina = nomeDisciplina;	
		this.qtdNotas = qtdNotas;
		this.notas = new double[this.qtdNotas];
		this.pesos = new int[this.qtdNotas];
		Arrays.fill(this.pesos, 1);
	}
	/**
	 * Constrói a Disciplina a partir do nome, qtd. de notas e pesos de cada nota (Parte 5.1 do Lab02, bônus).
	 * @param nomeDisciplina nome da disciplina.
	 * @param qtdNotas quantidade de notas na disciplina.
	 * @param arrayPesos pesos para cada nota da disciplina.
	 */
	public Disciplina(String nomeDisciplina, int qtdNotas, int[] pesos) {
		this.nomeDisciplina = nomeDisciplina;	
		this.qtdNotas = qtdNotas;
		this.notas = new double[this.qtdNotas];
		this.pesos = pesos;
	}
	/**
	 * Recebe o total de horas.
	 * @param horas horas estudadas.
	 */
	public void cadastraHoras(int horas) {
		this.horas += horas;
	}
	/**
	 * Recebe a nota a ser cadastrada (entre 1 e 4) e atribui a ela um valor.
	 * @param nota a nota a ser atualizada.
	 * @param valorNota o valor da nota a ser atualizada.
	 */
	
	public void cadastraNota(int nota, double valorNota) {
		this.notas[nota-1] = valorNota;
	}
	/**
	 * Retorna se o aluno foi aprovado ou não, sabendo-se que ele só é aprovado se sua média for igual ou superior a 7.0.
	 * @return true se o aluno foi aprovado e false se não foi.
	 */
	public boolean aprovado() {
		double soma = 0;
		int somaPesos = 0;
		
		for (int i = 0; i < qtdNotas; i++) {
			soma += this.notas[i] * this.pesos[i];
			somaPesos += this.pesos[i];
		}
		
		this.media = soma / somaPesos;
		
		if (this.media >= 7) {
			return true;
		}
		
		return false;
		
	}
	/**
	 * Retorna a formatação em string do nome da disciplina, horas estudadas, média geral e array de notas totais.
	 * @return String com as características da disciplina.
	 */
	@Override
	public String toString() {
		return String.format(Locale.US, "%s %d %.1f %s", this.nomeDisciplina, this.horas, this.media, Arrays.toString(this.notas));
	}
}
