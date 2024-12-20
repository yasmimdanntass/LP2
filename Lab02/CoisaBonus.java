package br.edu.ufcg.computacao.p2lp2.coisa;

import java.util.Arrays;

/**
 * Classe com main para testar as funcionalidades bônus propostas.
 * 
 * @author Yasmim Dantas da Costa Souza
 */
public class CoisaBonus {
	
	/**
	 * Main para executar as funcionalidades alteradas nas classes "Disciplina", "Descanso" e "Registro Resumos". 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		controlarDisciplina1();
		System.out.println("-----");
		controlarDisciplina2();
		System.out.println("-----");
		registrarResumos1();
		System.out.println("-----");
		registrarDescanso1();
	}
	
	/**
	 * Método para instanciar o objeto "economia" com o parâmetro "qtdNotas" = 5 como extra. (bônus 5.1)
	 */
	private static void controlarDisciplina1() {
		Disciplina economia = new Disciplina("ECONOMIA", 5);
		economia.cadastraHoras(10);
		economia.cadastraNota(1, 5.0);
		economia.cadastraNota(2, 6.0);
		economia.cadastraNota(3, 7.0);
		System.out.println(economia.aprovado());
		economia.cadastraNota(4, 10.0);
		System.out.println(economia.aprovado());
		economia.cadastraNota(5, 10.0);
		System.out.println(economia.aprovado());
		System.out.println(economia.toString());
	}
	
	/**
	 * Método para instanciar o objeto "fmcc2" com o parâmetro "qtdNotas" = 5 como extra e o array de pesos inteiros {2, 4, 3, 1, 6}. (bônus 5.1)
	 */
	private static void controlarDisciplina2() {
		Disciplina fmcc2 = new Disciplina("FMCC 2", 5, new int[] {8, 9, 10, 7, 2});
		fmcc2.cadastraHoras(10);
		fmcc2.cadastraNota(1, 5.0);
		fmcc2.cadastraNota(2, 6.0);
		fmcc2.cadastraNota(3, 7.0);
		System.out.println(fmcc2.aprovado());
		fmcc2.cadastraNota(4, 10.0);
		System.out.println(fmcc2.aprovado());
		fmcc2.cadastraNota(5, 10.0);
		System.out.println(fmcc2.aprovado());
		System.out.println(fmcc2.toString());
	}
	
	/**
	 * Método para instanciar o objeto "resumosFeitos" com o parâmetro "numeroDeResumos" = 100 e os resumos sobre "Classes" 
	 * e "Tipo", para que se possa fazer uma busca de em quais foram encontradas as palavras "a" e "identifica". (bônus 5.2)
	 */
	private static void registrarResumos1() {
		RegistroResumos meusResumos = new RegistroResumos(100); 
		meusResumos.adiciona("Tipo", "Identifica a semântica (operações e significados) de um conjunto de dados.");
		meusResumos.adiciona("Classes", "Classes definem um tipo e a base de código para criação de objetos.");
		System.out.println(Arrays.toString(meusResumos.busca("a")));
		System.out.println(Arrays.toString(meusResumos.busca("identifica")));
	}
	
	/**
	 *  Método para instanciar o objeto "descanso" com o emoji "<(^_^<)" como representador.
	 */ 
	public static void registrarDescanso1() {
		Descanso descanso = new Descanso();
		descanso.definirEmoji("<(^_^<)");
		System.out.println(descanso.getStatusGeral());
		descanso.defineHorasDescanso(30); // altera o status inicial
		descanso.defineNumeroSemanas(1);
		System.out.println(descanso.getStatusGeral());
		descanso.definirEmoji("<(^_^<)");
		descanso.defineHorasDescanso(300); // mantém o status inicial
		descanso.defineNumeroSemanas(1);
		System.out.println(descanso.getStatusGeral());
	}
}
