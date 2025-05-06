package br.edu.ufcg.computacao.mrbet;

import java.util.Objects;

/**
 * Representa uma aposta realizada por um time em um campeonato.
 * A aposta contém informações sobre o time que realizou a aposta, o campeonato em que a aposta foi feita, 
 * a colocação apostada e o valor da aposta.
 * 
 * @author Yasmim Dantas da Costa Souza - Matrícula: 124110564
 */
public class Aposta {
	
    /** O time que está realizando a aposta */
	private Time time;
	
    /** O campeonato em que a aposta foi realizada */
	private Campeonato campeonato;
	
    /** A colocação apostada no campeonato */
	private int colocacao;
	
    /** O valor da aposta */
	private double valor;
	
    /**
     * Construtor que cria uma aposta com as informações do time, campeonato, colocação e valor.
     * 
     * @param time O time que realiza a aposta.
     * @param campeonato O campeonato em que a aposta foi realizada.
     * @param colocacao A colocação apostada no campeonato.
     * @param valor O valor da aposta.
     */
	public Aposta(Time time, Campeonato campeonato, int colocacao, double valor) {
		this.time = time;
		this.campeonato = campeonato;
		this.colocacao = colocacao;
		this.valor = valor;
	}

    /**
     * Gera o código hash para a aposta com base no time, campeonato, colocação e valor.
     * 
     * @return O código hash gerado para a aposta.
     */
	@Override
	public int hashCode() {
		return Objects.hash(campeonato, colocacao, time, valor);
	}

    /**
     * Compara se duas apostas são iguais, levando em consideração o time, campeonato, colocação e valor da aposta.
     * 
     * @param obj O objeto a ser comparado com a aposta atual.
     * @return Retorna `true` se as apostas forem iguais, caso contrário `false`.
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aposta other = (Aposta) obj;
		return Objects.equals(campeonato, other.campeonato) && colocacao == other.colocacao
				&& Objects.equals(time, other.time)
				&& Double.doubleToLongBits(valor) == Double.doubleToLongBits(other.valor);
	}
	
    /**
     * Retorna uma representação em string da aposta, incluindo o time, campeonato, colocação e valor da aposta.
     * 
     * @return A string representando a aposta.
     */
	@Override
	public String toString() {
		return  "[" + time.getCod() + "] " + time.getNome() + "/" + time.getMascote() + "\n" + campeonato.getNome() + 
				"\n" + colocacao + "/" + campeonato.getQtdParticipantes() + "\n" + "R$ " + String.format("%.2f", valor) + "\n";
	}

    /**
     * Retorna o time que fez a aposta.
     * 
     * @return O time que realizou a aposta.
     */
	public Time getTime() {
		return time;
	}

    /**
     * Retorna o campeonato em que a aposta foi realizada.
     * 
     * @return O campeonato em que a aposta foi realizada.
     */
	public Campeonato getCampeonato() {
		return campeonato;
	}

    /**
     * Retorna a colocação apostada no campeonato.
     * 
     * @return A colocação apostada.
     */
	public int getColocacao() {
		return colocacao;
	}

    /**
     * Retorna o valor da aposta.
     * 
     * @return O valor da aposta.
     */
	public double getValor() {
		return valor;
	}
}
