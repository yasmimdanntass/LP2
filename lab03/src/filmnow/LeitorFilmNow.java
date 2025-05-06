package filmnow;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Lógica para ler de arquivos csv os dados necessários e povoar o sistema de filmes. 
 * 
 * @author eliane
 *
 */
public class LeitorFilmNow {

	private static final int COLUNA_POSICAO = 0;
	private static final int COLUNA_NOME = 1;
	private static final int COLUNA_ANO = 2;
	private static final int COLUNA_LOCAL = 3;


	/**
	 * Lê filmes de um arquivo CSV e os coloca no sistema.
	 * @param arquivoFilmes Caminho para arquivo contendo os filmes.
	 * @param fn O sistema FilmNow a manipular.
	 * @return O número de filmes adicionados ao sistema.
	 * @throws IOException Caso não tenhamos permissão de ler o arquivo.
	 * @throws FileNotFoundException Caso o arquivo não exista.
	 */
	public int carregaContatos(String arquivoFilmes, FilmNow fn) throws FileNotFoundException, IOException {
		int carregados = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(arquivoFilmes))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				carregados += 1;
				if (carregados == 1) {
					// pulamos a primeira linha, o cabeçalho
					continue;
				}
				String[] campos = linha.split(",");
				processaLinhaCsvFilmes(campos, fn);
			}
		}
		
		return carregados - 1;
	}

	
	/**
	 * Coloca no sistema os dados de uma linha do arquivo de filmes inicial. 
	 * 
	 * @param campos As informações lidas do csv. 
	 * @param fn O sistema FilmNow a manipular. 
	 */
	private void processaLinhaCsvFilmes(String[] campos, FilmNow fn) {
		int posicao = Integer.parseInt(campos[COLUNA_POSICAO]);
		String nome = campos[COLUNA_NOME].trim();
		String ano = campos[COLUNA_ANO].trim();
		String local = campos[COLUNA_LOCAL].trim();

		fn.cadastraFilme(posicao, nome, ano, local);
	}

}
