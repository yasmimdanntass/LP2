package br.edu.ufcg.computacao.mrbet;

import java.util.Objects;
import java.util.*;

/**
 * Representa um time no sistema MrBet. Cada time possui um código, nome, mascote 
 * e a lista de campeonatos nos quais está participando. A classe permite a manipulação 
 * dos atributos do time e suas participações em campeonatos.
 * 
 * @author Yasmim Dantas da Costa Souza - Matrícula: 124110564
 */
public class Time {
    
    /** Código do time */
	private String cod;
	
    /** Nome do time */
	private String nome;
	
    /** Mascote do time */
	private String mascote;
	
    /** Lista de campeonatos em que o time participa */
	private List<Campeonato> campeonatosParticipante = new ArrayList<Campeonato>();

    /**
     * Construtor que cria um time com um código, nome e mascote.
     * 
     * @param cod O código único do time.
     * @param nome O nome do time.
     * @param mascote O mascote do time.
     */
	public Time(String cod, String nome, String mascote) {
		this.cod = cod;
		this.nome = nome;
		this.mascote = mascote;
	}

    /**
     * Retorna o código do time.
     * 
     * @return O código do time.
     */
	public String getCod() {
		return cod;
	}

    /**
     * Retorna o nome do time.
     * 
     * @return O nome do time.
     */
	public String getNome() {
		return nome;
	}

    /**
     * Define o nome do time.
     * 
     * @param nome O novo nome do time.
     */
	public void setNome(String nome) {
		this.nome = nome;
	}

    /**
     * Retorna o mascote do time.
     * 
     * @return O mascote do time.
     */
	public String getMascote() {
		return mascote;
	}

    /**
     * Define o mascote do time.
     * 
     * @param mascote O novo mascote do time.
     */
	public void setMascote(String mascote) {
		this.mascote = mascote;
	}
	
    /**
     * Retorna a lista de campeonatos nos quais o time está participando.
     * 
     * @return A lista de campeonatos.
     */
	public List<Campeonato> getCampeonatosParticipante() {
		return campeonatosParticipante;
	}

    /**
     * Adiciona um campeonato à lista de campeonatos do time. 
     * O campeonato só será adicionado se ainda não estiver na lista.
     * 
     * @param campeonato O campeonato a ser adicionado ao time.
     */
	public void adicionaCampeonato(Campeonato campeonato) {
		if (!campeonatosParticipante.contains(campeonato)) {
			campeonatosParticipante.add(campeonato);
		}
	}
	
    /**
     * Retorna um array de strings representando os campeonatos nos quais o time participa.
     * 
     * @return Um array de strings com a descrição dos campeonatos.
     */
	public String[] exibeCampeonatos() {
		String[] textosCampeonatos = new String[campeonatosParticipante.size()];
		
		for (int i = 0; i < campeonatosParticipante.size(); i++) {
			textosCampeonatos[i] = "* " + campeonatosParticipante.get(i).toString();
		}
		
		return textosCampeonatos;
	}

    /**
     * Gera o código hash para o time com base no seu código.
     * 
     * @return O código hash gerado para o time.
     */
	@Override
	public int hashCode() {
		return Objects.hash(cod);
	}

    /**
     * Compara se dois objetos são iguais, levando em consideração o código do time.
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
		Time other = (Time) obj;
		return Objects.equals(cod, other.cod);
	}

    /**
     * Retorna uma representação em string do time, com seu código, nome e mascote.
     * 
     * @return A string representando o time.
     */
	@Override
	public String toString() {
		return "[" + cod + "] " + nome + " / " + mascote;
	}
}
