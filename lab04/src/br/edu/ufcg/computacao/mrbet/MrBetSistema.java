package br.edu.ufcg.computacao.mrbet;

import java.util.*;

/**
 * Classe que representa o sistema de apostas do MrBet, responsável por gerenciar times, campeonatos e apostas.
 * Permite a inclusão de times, campeonatos, a adição de times aos campeonatos e o registro de apostas.
 * @author Yasmim Dantas da Costa Souza - Matrícula 124110564
 */

public class MrBetSistema {
    
    /**
     * Mapa que armazena os times registrados, onde a chave é o código do time.
     */
    private HashMap<String, Time> times = new HashMap<String, Time>();
    
    /**
     * Mapa que armazena os campeonatos registrados, onde a chave é o nome do campeonato (em minúsculas).
     */
    private HashMap<String, Campeonato> campeonatos = new HashMap<String, Campeonato>();
    
    /**
     * Lista que armazena as apostas realizadas.
     */
    private ArrayList<Aposta> apostas = new ArrayList<Aposta>();
    
    /**
     * Construtor da classe MrBetSistema. Inicializa o sistema de apostas.
     */
    public MrBetSistema() {
        
    }
    
    /**
     * Inclui um novo time no sistema.
     * 
     * @param codigo O código do time.
     * @param nome O nome do time.
     * @param mascote O mascote do time.
     * @return Uma mensagem indicando o resultado da inclusão.
     * @throws IllegalArgumentException Se algum argumento for nulo ou vazio.
     */
    public String incluiTime(String codigo, String nome, String mascote) {
        if (codigo == null || nome == null || mascote == null) {
            throw new IllegalArgumentException("Argumento nulo!");
        }
        
        if (codigo.trim().equals("") || nome.trim().equals("") || mascote.trim().equals("")) {
            throw new IllegalArgumentException("Argumento vazio!");
        }
        
        if (verificaTime(codigo)) {
            return "TIME JÁ EXISTE!";
        }
        
        Time novo = new Time(codigo, nome, mascote);
        times.put(codigo, novo);
        return "INCLUSÃO REALIZADA";
    }
    
    /**
     * Recupera as informações de um time.
     * 
     * @param codigo O código do time.
     * @return Uma string contendo as informações do time.
     * @throws IllegalArgumentException Se o código do time for nulo ou vazio.
     */
    public String recuperaTime(String codigo) {
        if (codigo == null ) {
            throw new IllegalArgumentException("Argumento nulo!");
        }
        
        if (codigo.trim().equals("")) {
            throw new IllegalArgumentException("Argumento vazio!");
        }
        
        if (!verificaTime(codigo)) {
            return "TIME NÃO EXISTE!";
        }
        
        Time time = times.get(codigo);
        return time.toString();
    }
    
    /**
     * Adiciona um novo campeonato ao sistema.
     * 
     * @param nome O nome do campeonato.
     * @param qtdParticipantes A quantidade de participantes no campeonato.
     * @return Uma mensagem indicando o resultado da adição.
     * @throws IllegalArgumentException Se o nome for nulo ou vazio, ou a quantidade de participantes for inválida.
     */
    public String adicionaCampeonato(String nome, int qtdParticipantes) {
        if (nome == null) {
            throw new IllegalArgumentException("Argumento nulo!");
        }
        
        if (nome.trim().equals("")) {
            throw new IllegalArgumentException("Argumento vazio!");
        }
        
        if (qtdParticipantes <= 0) {
            throw new IllegalArgumentException("Número de participantes inválido!");
        }
        
        if (verificaCampeonato(nome.toLowerCase())) {
            return "CAMPEONATO JÁ EXISTE!";
        }
        
        Campeonato novo = new Campeonato(nome, qtdParticipantes);
        campeonatos.put(nome.toLowerCase(), novo);
        return "CAMPEONATO ADICIONADO!";
    }
    
    /**
     * Inclui um time em um campeonato existente.
     * 
     * @param codigo O código do time.
     * @param nomeCampeonato O nome do campeonato.
     * @return Uma mensagem indicando o resultado da operação.
     * @throws IllegalArgumentException Se algum argumento for nulo ou vazio.
     */
    public String incluiTimeEmCampeonato(String codigo, String nomeCampeonato) {
        if (codigo == null || nomeCampeonato == null) {
            throw new IllegalArgumentException("Argumento nulo!");
        }
        
        if (codigo.trim().equals("") || nomeCampeonato.trim().equals("")) {
            throw new IllegalArgumentException("Argumento vazio!");
        }
        
        if (!verificaTime(codigo)) {
            return "O TIME NÃO EXISTE!"; 
        }
        
        if (!verificaCampeonato(nomeCampeonato.toLowerCase())) {
            return "O CAMPEONATO NÃO EXISTE!"; 
        }
        
        if (!campeonatos.get(nomeCampeonato.toLowerCase()).verificaPodeAdicionarMaisTime()){
            return "TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!";
        }
        
        Campeonato camp = campeonatos.get(nomeCampeonato.toLowerCase());
        Time time = times.get(codigo);
        
        camp.adicionaTime(times.get(codigo));
        time.adicionaCampeonato(camp);
        return "TIME INCLUÍDO NO CAMPEONATO!";
    }
    
    /**
     * Verifica se um time está participando de um campeonato.
     * 
     * @param codigo O código do time.
     * @param nomeCampeonato O nome do campeonato.
     * @return Uma mensagem indicando se o time está ou não no campeonato.
     * @throws IllegalArgumentException Se algum argumento for nulo ou vazio.
     */
    public String verificaTimeEmCampeonato(String codigo, String nomeCampeonato) {
        if (codigo == null || nomeCampeonato == null) {
            throw new IllegalArgumentException("Argumento nulo!");
        }
        
        if (codigo.trim().equals("") || nomeCampeonato.trim().equals("")) {
            throw new IllegalArgumentException("Argumento vazio!");
        }
        
        if (!verificaTime(codigo)) {
            return "O TIME NÃO EXISTE!"; 
        }
        
        if (!verificaCampeonato(nomeCampeonato.toLowerCase())) {
            return "O CAMPEONATO NÃO EXISTE!"; 
        }
        
        Campeonato camp = campeonatos.get(nomeCampeonato.toLowerCase());
        Time time = times.get(codigo);
        
        if (camp.verificaTime(time)) {
            return "O TIME ESTÁ NO CAMPEONATO!";
        }
        
        return "O TIME NÃO ESTÁ NO CAMPEONATO!";
    }
    
    /**
     * Exibe os campeonatos em que um time está participando.
     * 
     * @param codigo O código do time.
     * @return Uma string contendo os campeonatos em que o time participa.
     * @throws IllegalArgumentException Se o código do time for nulo ou vazio.
     */
    public String exibeCampeonatosTime(String codigo) {
        if (codigo == null) {
            throw new IllegalArgumentException("Argumento nulo!");
        }
        
        if (codigo.trim().equals("")) {
            throw new IllegalArgumentException("Argumento vazio!");
        }
        
        if (!verificaTime(codigo)) {
            return "O TIME NÃO EXISTE!"; 
        }
        
        String retorno = "";
        
        for (String s : times.get(codigo).exibeCampeonatos()) {
            retorno += s + "\n";
        }
        
        return retorno;
    }
    
    /**
     * Realiza uma aposta em um campeonato.
     * 
     * @param codigo O código do time.
     * @param nomeCampeonato O nome do campeonato.
     * @param colocacao A colocação desejada para o time.
     * @param valor O valor da aposta.
     * @return Uma mensagem indicando o resultado da aposta.
     * @throws IllegalArgumentException Se algum argumento for nulo ou vazio, ou se a colocação ou o valor forem inválidos.
     */
    public String aposta(String codigo, String nomeCampeonato, int colocacao, double valor) {
        if (codigo == null || nomeCampeonato == null) {
            throw new IllegalArgumentException("Argumento nulo!");
        }
        
        if (codigo.trim().equals("") || nomeCampeonato.trim().equals("")) {
            throw new IllegalArgumentException("Argumento vazio!");
        }
        
        if (colocacao <= 0 || valor <= 0) {
            throw new IllegalArgumentException("Colocação ou valor inválido(s)!");
        }
        
        if (!verificaTime(codigo)) {
            return "O TIME NÃO EXISTE!"; 
        }
        
        if (!verificaCampeonato(nomeCampeonato.toLowerCase())) {
            return "O CAMPEONATO NÃO EXISTE!"; 
        }
        
        Time time = times.get(codigo);
        Campeonato camp = campeonatos.get(nomeCampeonato.toLowerCase());
        
        if (!camp.verificaTime(time)) {
            return "O TIME NÃO ESTÁ NO CAMPEONATO";
        }
        
        if (colocacao > camp.getQtdParticipantes()) {
            return "APOSTA NÃO REGISTRADA!";
        }
        
        Aposta nova = new Aposta(time, camp, colocacao, valor);
        apostas.add(nova);
        
        return "APOSTA REGISTRADA!";
    }
    
    /**
     * Exibe o status de todas as apostas registradas no sistema.
     * 
     * @return Uma string contendo todas as apostas registradas.
     */
    public String statusApostas() {
        String retorno = "Apostas: \n \n";
        
        for (int i = 0; i < apostas.size(); i++) {
            retorno += (i+1) + ". " + apostas.get(i).toString();
        }
    
        return retorno;
    }
    
    /**
     * Verifica se um time está registrado no sistema.
     * 
     * @param codigo O código do time.
     * @return True se o time existir, false caso contrário.
     * @throws IllegalArgumentException Se o código for nulo ou vazio.
     */
    private boolean verificaTime(String codigo) {
        if (codigo == null) {
            throw new IllegalArgumentException("Argumento nulo!");
        }
        
        if (codigo.trim().equals("")) {
            throw new IllegalArgumentException("Argumento vazio!");
        }
        
        return times.containsKey(codigo);
    }
    
    /**
     * Verifica se um campeonato está registrado no sistema.
     * 
     * @param nome O nome do campeonato.
     * @return True se o campeonato existir, false caso contrário.
     * @throws IllegalArgumentException Se o nome for nulo ou vazio.
     */
    private boolean verificaCampeonato(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Argumento nulo!");
        }
        
        if (nome.trim().equals("")) {
            throw new IllegalArgumentException("Argumento vazio!");
        }
        
        return campeonatos.containsKey(nome);
    }
}

