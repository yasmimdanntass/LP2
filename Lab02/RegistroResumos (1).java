package br.edu.ufcg.computacao.p2lp2.coisa;

import java.util.*;

/**
* Representação do registro de resumos no sistema. Cada resumo possui tema e existe uma quantidade 
* limitada de resumos a serem adicionados.
* 
* @author Yasmim Dantas da Costa Souza
*/
public class RegistroResumos {
	
	private Resumos[] resumos;
	private int contador;
	private int numeroDeResumos;
	
	/**
	 * Constrói o registro do resumo a partir da quantidade limite de resumos.
	 *
	 * @param numeroDeResumos: quantidade limite de resumos.
	 */
	public RegistroResumos(int numeroDeResumos) {
		this.resumos = new Resumos[numeroDeResumos];
		this.contador = 0;
		this.numeroDeResumos = numeroDeResumos;
		
	}

	/**
	 * Adiciona um resumo ao registro, com tema e conteúdo.
	 * 
	 * @param tema: String representando o assunto.
	 * @param resumo: String representando o conteúdo.
	 */
	public void adiciona(String tema, String conteudo) {
		Resumos resumoAdicionado = new Resumos(tema, conteudo);
		
		if (!temResumo(tema)) {
			this.resumos[this.contador % this.numeroDeResumos] = resumoAdicionado;
			this.contador += 1;
		}
	}
	
	/**
	 * Conta a quantidade de resumos cadastrados no sistema.
	 * @return ou o número de resumos total, caso o contador ultrapasse essa quantidade, ou o próprio contador.
	 */
	public int conta() {
		if (this.contador > this.numeroDeResumos) {
			return this.numeroDeResumos;
		} else {
			return this.contador;
		}
	}
	
	/**
	 * Retorna um array de strings contendo o resumo com tema e conteúdo.
	 * @return resumosFormatados: array de strings contendo tema e conteúdo formatados.
	 */
	public String[] pegaResumos() {
		String[] resumosFormatados = new String[conta()];
	
		for (int i = 0; i < conta(); i++) {
			resumosFormatados[i] = resumos[i].toString();
				
			}
		
		return resumosFormatados;
	}
	
	/**
	 * Retorna a quantidade de resumos cadastrados e os temas deles.
	 * @return String formatada contendo as informações acima.
	 */
	public String imprimeResumos() {

		String saida = this.resumos[0].getTema();
		
		for (int i = 1; i < conta(); i++) {
			saida += " | " + this.resumos[i].getTema();
		}
		
		return String.format("- %d resumo(s) cadastrado(s) \n%s", conta(), saida);
			
	}
	/**
	 * Retorna um valor booleano (true ou false) para avaliar se determinado tema está presente dentre os resumos.
	 * @param tema
	 * @return true ou false
	 */
	public boolean temResumo(String tema) {
		
		for (int i = 0; i < conta(); i++) {
			if (resumos[i].getTema().equals(tema)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Procura determinada palavra-chave dentre os resumos disponíveis.
	 * @param chaveDeBusca: palavra-chave procurada
	 * @return array ordenado com os temas que abrangem essa palavra-chave.
	 */
	public String[] busca(String chaveDeBusca) {
		String chave = chaveDeBusca.toLowerCase();
		ArrayList<String> temasEncontrados = new ArrayList<>();
		
		for (int i = 0; i < conta(); i++) {
			
			for (String palavra : resumos[i].getConteudo().split(" ")) {
				if (palavra.toLowerCase().equals(chave)) {
					temasEncontrados.add(resumos[i].getTema());
					
				}
			}
		}
		
		Set<String> temasUnicos = new HashSet<>(temasEncontrados);
		ArrayList<String> temasOrdenados = new ArrayList<>(temasUnicos);
        Collections.sort(temasOrdenados);
        return temasOrdenados.toArray(new String[0]);
				
	}
}

	
