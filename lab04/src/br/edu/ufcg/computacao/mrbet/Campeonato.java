package br.edu.ufcg.computacao.mrbet;

import java.util.*;

/**
 * Representa um campeonato no sistema MrBet. Cada campeonato possui um nome, 
 * uma quantidade total de participantes e um conjunto de times participantes.
 * O campeonato permite adicionar times, verificar se um time já está inscrito, 
 * e se é possível adicionar mais times.
 * 
 * @author Yasmim Dantas da Costa Souza - Matrícula: 124110564
 */
public class Campeonato {
    
    /** Nome do campeonato */
	private String nome;
	
    /** Quantidade total de participantes permitidos no campeonato */
	private int qtdParticipantes;
	
    /** Quantidade de participantes já adicionados ao campeonato */
	private int qtdParticipantesAdicionados;
	
    /** Conjunto de times que estão participando do campeonato */
	private HashSet<Time> timesParticipantes = new HashSet<Time>();

    /**
     * Construtor que cria um campeonato com um nome e uma quantidade máxima de participantes.
     * 
     * @param nome O nome do campeonato.
     * @param qtdParticipantes A quantidade máxima de times participantes no campeonato.
     */
	public Campeonato(String nome, int qtdParticipantes) {
		this.nome = nome;
		this.qtdParticipantes = qtdParticipantes;
	}

    /**
     * Retorna o nome do campeonato.
     * 
     * @return O nome do campeonato.
     */
	public String getNome() {
		return nome;
	}
	
    /**
     * Retorna a quantidade total de participantes permitidos no campeonato.
     * 
     * @return A quantidade total de participantes.
     */
	public int getQtdParticipantes() {
		return qtdParticipantes;
	}

    /**
     * Define a quantidade total de participantes permitidos no campeonato.
     * 
     * @param qtdParticipantes A nova quantidade total de participantes.
     */
	public void setQtdParticipantes(int qtdParticipantes) {
		this.qtdParticipantes = qtdParticipantes;
	}
	
    /**
     * Retorna a quantidade de participantes que foram adicionados ao campeonato.
     * 
     * @return A quantidade de participantes adicionados.
     */
	public int getQtdParticipantesAdicionados() {
		return qtdParticipantesAdicionados;
	}

    /**
     * Retorna o conjunto de times que estão participando do campeonato.
     * 
     * @return O conjunto de times participantes.
     */
	public HashSet<Time> getTimesParticipantes() {
		return timesParticipantes;
	}

    /**
     * Adiciona um time ao campeonato. Caso o campeonato já tenha atingido o limite de participantes, 
     * o time não será adicionado.
     * 
     * @param time O time a ser adicionado ao campeonato.
     */
	public void adicionaTime(Time time) {
		this.timesParticipantes.add(time);
		this.qtdParticipantesAdicionados++;
	}
	
    /**
     * Verifica se um time já está participando do campeonato.
     * 
     * @param time O time a ser verificado.
     * @return Retorna `true` se o time já estiver participando, caso contrário `false`.
     */
	public boolean verificaTime(Time time) {
		return timesParticipantes.contains(time);
	}
	
    /**
     * Verifica se ainda é possível adicionar mais times ao campeonato.
     * 
     * @return Retorna `true` se ainda houver vagas para mais times, caso contrário `false`.
     */
	public boolean verificaPodeAdicionarMaisTime() {
		return this.qtdParticipantesAdicionados < qtdParticipantes;
	}
	
    /**
     * Gera o código hash para o campeonato com base no nome.
     * 
     * @return O código hash gerado para o campeonato.
     */
	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

    /**
     * Compara se dois objetos são iguais, levando em consideração o nome do campeonato.
     * 
     * @param obj O objeto a ser comparado com o atual.
     * @return Retorna `true` se os objetos forem iguais, caso contrário `false`.
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campeonato other = (Campeonato) obj;
		return Objects.equals(nome, other.nome);
	}
	
    /**
     * Retorna uma representação em string do campeonato, com o nome e a quantidade de times já adicionados.
     * 
     * @return A string representando o campeonato.
     */
	@Override
	public String toString() {
		return this.nome + " - " + qtdParticipantesAdicionados + "/" + qtdParticipantes;
	}
}
