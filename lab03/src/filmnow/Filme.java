
package filmnow;

import java.util.Objects;

/**
 * Classe para instanciar um objeto filme, com os atributos: Nome, Ano, Local e StatusHot.
 * 
 * @author Yasmim Dantas
 */
public class Filme {
	
	private String nome;
	private String ano;
	private String local;
	private boolean statusHot;
	
	/**
	 * Constrói o filme a partir dos parâmetros.
	 * @param nome nome do filme
	 * @param ano ano de lançamento
	 * @param local local de lançamento
	 */
	public Filme(String nome, String ano, String local) {
		if (nome == null) {
            throw new NullPointerException("Nome não pode ser nulo");
        }
		
		if (nome.trim().isEmpty()){
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
		
		if (local == null) {
            throw new NullPointerException("Local não pode ser nulo");
        }
		
		if (local.trim().isEmpty()){
            throw new IllegalArgumentException("Local não pode ser vazio");
        }
		
		this.nome = nome;
		this.ano = ano;
		this.local = local;
		this.statusHot = false;
		
	}
	
	/**
	 * Retorna o ano de lançamento.
	 * @return ano
	 */
	public String getAno() {
		return ano;
	}

	/**
	 * Modifica o ano de lançamento.
	 * @param ano
	 */
	public void setAno(String ano) {
		this.ano = ano;
	}

	/**
	 * Retorna o local de lançamento
	 * @return local
	 */
	public String getLocal() {
		return local;
	}

	/**
	 * Modifica o local de lançamento
	 * @param local
	 */
	public void setLocal(String local) {
		this.local = local;
	}

	/**
	 * Retorna o nome do filme
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Modifica o nome do filme
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Modifica o status do filme para Hot ou não, baseado em valores booleanos.
	 * @param status true ou false
	 */
	public void setHot(boolean status) {
		this.statusHot = status;
	}
	
	/**
	 * Retorna o status do filme
	 * @return true ou false
	 */
	public boolean getHot() {
		return this.statusHot;
	}
	
	/**
	 * Gera o hashCode do filme baseado no ano e nome.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(ano, nome);
	}
	
	/**
	 * Compara filmes baseado no nome e ano de lançamento.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		return ano == other.ano && Objects.equals(nome, other.nome);
	}

	/**
	 * Retorna uma string detalhando o filme com o nome e o ano, caso presente.
	 */
	@Override
	public String toString() {
		if (this.ano.trim().isEmpty()) {
			return this.nome;
		}
		return this.nome + ", " + this.ano;
		
	}

}
