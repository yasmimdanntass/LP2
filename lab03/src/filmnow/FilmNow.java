package filmnow;

/**
 * Sistema que mantém os seus filmes prediletos. Podem existir 100 filmes. 
 * 
 * @author eliane
 *
 */
public class FilmNow {
	
	private static final int TAMANHO = 100;
	private static final int TAMANHO_HOTLIST = 10;
	private Filme[] filmes;
	private Filme[] hotlist;

	/**
	 * Constrói FilmNow atribuindo tamanhos de 100 e 10 para o array geral de filmes e para o array da hotlist, respectivamente.
	 */
	public FilmNow() {
		this.filmes = new Filme[TAMANHO];
		this.hotlist = new Filme[TAMANHO_HOTLIST];
	}
	
	/**
	 * Adiciona filme no array de filmes, verificando se os parâmetros passados foram adequados.
	 * @param posicao  posição no sistema
	 * @param nome  nome do filme
	 * @param ano  ano de lançamento
	 * @param local local de lançamento
	 * @return mensagem avisando sobre o resultado do procedimento
	 */
	public String cadastraFilme(int posicao, String nome, String ano, String local) {
		if (posicao <= 0 || posicao > TAMANHO) {
	        return "POSIÇÃO INVÁLIDA";
	    }
		
		if (nome.trim().isEmpty() || local.trim().isEmpty()) {
	        return "FILME INVÁLIDO";
	    }
	
		Filme novoFilme = new Filme(nome, ano, local);
	
		if (verificaFilmeExistenteFilmes(novoFilme)) {
				return "FILME JÁ ADICIONADO";
		}
		
		this.filmes[posicao - 1] = novoFilme;
		return "FILME ADICIONADO";
	}
	
	/**
	 * Retorna todos os filmes adicionados no sistema, indicados por seus índices
	 * @return string com os filmes
	 */
	public String exibeFilmes() {
		String retorno = "";
		for (int i = 0; i < TAMANHO; i++) {
			if (filmes[i] != null) {
				retorno += formataFilme(i+1, filmes[i]) + "\n";
			}
		}
		return retorno;
	}
	
	/**
	 * Analisa se existe filme na posição indicada e, caso exista, retorna uma string com nome, ano de lançamento e local de lançamento
	 * @param posicao
	 * @return
	 */
	public String detalhaFilme(int posicao) {
		String retorno = "";
		if (posicao <= 0 || posicao >= TAMANHO) {
	        return "POSIÇÃO INVÁLIDA";
	    }
		if (this.filmes[posicao-1] == null) {
			return retorno;
		}
		
		if (this.filmes[posicao-1].getHot()) {
			retorno += "🔥 ";
		}
		
		return retorno + this.filmes[posicao-1].toString() + "\n" + this.filmes[posicao-1].getLocal();
		}
	
	/**
	 * Adiciona filme na hotlist, verificando se existe filme na posição indicada, a validade das posições e alterando os statusHot dos filmes envolvidos.
	 * @param posicaoFilme posição do filme a ser adicionado
	 * @param posicaoHotList posição do filme na hotlist
	 * @return mensagem de resultado da operação
	 */
	public String adicionaFilmeHotList(int posicaoFilme, int posicaoHotList) {
	    if (posicaoFilme <= 0 || posicaoFilme > TAMANHO || posicaoHotList <= 0 || posicaoHotList > TAMANHO_HOTLIST) {
	        return "POSIÇÃO INVÁLIDA";
	    }

	    if (this.filmes[posicaoFilme - 1] == null) {
	        return "FILME NÃO ADICIONADO ANTERIORMENTE";
	    }
	    
	    if (verificaFilmeExistenteHotList(this.filmes[posicaoFilme - 1])) {
	    	return "FILME JÁ ADICIONADO NA HOTLIST";
	    }
	    
	    if (this.hotlist[posicaoHotList - 1] != null) {
	        this.hotlist[posicaoHotList - 1].setHot(false);
	    }

	    this.hotlist[posicaoHotList - 1] = this.filmes[posicaoFilme - 1];
	    this.filmes[posicaoFilme - 1].setHot(true);

	    return "FILME ADICIONADO NA HOTLIST";
	}
	
	/**
	 * Exibe todos os filmes presentes na hotlist
	 * @return string contendo os filmes presentes na hotlist
	 */
	public String exibeHotList() {
		String retorno = "";
		for (int i = 0; i < TAMANHO_HOTLIST; i++) {
			if (this.hotlist[i] != null) {
				retorno += formataFilme(i+1, this.hotlist[i]) + "\n";
			}
		}
		return retorno;
	}
	
	/**
	 * Formata uma string com a posição dele, nome e ano de lançamento, caso exista.
	 * @param posicao  posição do filme buscado
	 * @param filme  objeto filme a ser retratado
	 * @return string com informações dos filmes presentes
	 */
	private String formataFilme(int posicao, Filme filme) {
		return posicao + " - " + filme.toString();
	}
	
	/**
	 * Verifica se o filme passado já está presente no array de filmes do sistema
	 * @param filme2  filme a ser procurado
	 * @return true ou false
	 */
	private boolean verificaFilmeExistenteFilmes(Filme filme2) {
	    for (Filme f : this.filmes) {
	        if (f != null && f.equals(filme2)) {
	            return true;
	        }
	    }
	    
	    return false;
	}
	
	/**
	 * Verifica se o filme passado já foi adicionado na hotlist
	 * @param filme  filme a ser procurado
	 * @return true ou false
	 */
	private boolean verificaFilmeExistenteHotList(Filme filme) {
	    for (Filme f : this.hotlist) {
	        if (f != null && f.equals(filme)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	/**
	 * Remove filme da hotlist, atualizando seu statusHot para false.
	 * @param posicaoHotList posição do filme a ser procurado
	 * @return String contendo o resultado da operação
	 */
	public String removeFilmeHotList(int posicaoHotList) {
		if (posicaoHotList <= 0 || posicaoHotList >= TAMANHO_HOTLIST) {
	        return "POSIÇÃO INVÁLIDA";
	    }
		 
		if (this.hotlist[posicaoHotList-1] != null) {
			this.hotlist[posicaoHotList-1].setHot(false);
			this.hotlist[posicaoHotList-1] = null;
			return "FILME REMOVIDO DA HOTLIST";
		}	
		return "FILME NÃO EXISTENTE NA HOTLIST";
		
	}
	/**
	 * Retorna o array de filmes.
	 * @return clone do array de filmes
	 */
	public Filme[] getFilmes() {
		return this.filmes;
	}
	
	/**
	 * Retorna um filme de determinada posição
	 * @param posicao posição do filme
	 * @return o filme da posição indicada
	 */
	public Filme getFilme(int posicao) {
		return this.filmes[posicao-1];
	}
	
	/**
	 * Retorna a hotlist dos filmes
	 * @return hotlist
	 */
	public Filme[] getHotList() {
		return this.hotlist;
	}
}

