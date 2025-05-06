package br.edu.ufcg.computacao.mrbet;

import java.util.Scanner;

/**
 * A classe principal que implementa o sistema de apostas MrBet.
 * O sistema permite realizar diversas operações, como inclusão de times, campeonatos, 
 * verificação de times em campeonatos, e registro de apostas.
 * 
 * @author Yasmim Dantas da Costa Souza - Matrícula: 124110564
 */
public class MainMrBet {

    /**
     * Método principal que inicia a aplicação e exibe o menu interativo.
     * O usuário pode interagir com o sistema por meio de comandos no terminal.
     * 
     * @param args Argumentos da linha de comando, não utilizados nesta aplicação.
     */
	public static void main(String[] args) {
		MrBetSistema mb = new MrBetSistema();
		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while (true) {
			escolha = menu(scanner);
			comando(escolha, mb, scanner);
		}
	}

    /**
     * Exibe o menu principal de opções para o usuário interagir com o sistema.
     * O usuário pode escolher entre várias opções de operações, como incluir time, 
     * recuperar time, adicionar campeonato, etc.
     * 
     * @param scanner Objeto Scanner para ler a entrada do usuário.
     * @return A opção escolhida pelo usuário.
     */
	private static String menu(Scanner scanner) {
		System.out.println(
				"\n---\nMENU\n" + 
						"(M)Minha inclusão de times\n" + 
						"(R)Recuperar time\n" + 
						"(.)Adicionar campeonato\n" + 
						"(B)Bora incluir time em campeonato e Verificar se time está em campeonato\n" + 
						"(E)Exibir campeonatos que o time participa\n" +
						"(T)Tentar a sorte e status\n" +
						"(!)Já pode fechar o programa!\n" + 
						"\n" + 
						"Opção> ");
		return scanner.nextLine().toUpperCase();
	}
	
    /**
     * Executa o comando correspondente à opção escolhida pelo usuário no menu.
     * Chama o método específico para cada operação de acordo com a escolha.
     * 
     * @param opcao A opção escolhida pelo usuário.
     * @param mb O objeto que representa o sistema MrBet.
     * @param scanner Objeto Scanner para ler a entrada do usuário.
     */
	private static void comando(String opcao, MrBetSistema mb, Scanner scanner) {
	    switch (opcao) {
	        case "M":
	            incluirTime(mb, scanner);
	            break;
	        case "R":
	            recuperarTime(mb, scanner);
	            break;
	        case ".":
	            adicionarCampeonato(mb, scanner);
	            break;
	        case "B":
	        	incluirTimeOuVerificarTime(mb, scanner);
	            break;
	        case "E":
	            exibirCampeonatosTime(mb, scanner);
	            break;
	        case "T":
	            apostarOuStatus(mb, scanner);
	            break;
	        case "!":
	            sai();
	            break;
	        default:
	        	System.out.println("OPÇÃO INVÁLIDA!");
	    }
	}

    /**
     * Exibe o submenu para incluir um time em um campeonato ou verificar se um time está em um campeonato.
     * 
     * @param mb O objeto que representa o sistema MrBet.
     * @param scanner Objeto Scanner para ler a entrada do usuário.
     */
	private static void incluirTimeOuVerificarTime(MrBetSistema mb, Scanner scanner) {
		System.out.println("\n(I) Incluir time em campeonato\n(V) Verificar se time está em campeonato?");
        String opcao1 = scanner.nextLine().trim().toUpperCase();
        if (opcao1.equals("I")) {
            incluirTimeEmCampeonato(mb, scanner);    
        } else if (opcao1.equals("V")) {
            verificarTimeEmCampeonato(mb, scanner);
        } else {
        	System.out.println("OPÇÃO INVÁLIDA!");	
        }
	}
	
    /**
     * Permite incluir um time no sistema.
     * Solicita ao usuário o código, nome e mascote do time.
     * 
     * @param mb O objeto que representa o sistema MrBet.
     * @param scanner Objeto Scanner para ler a entrada do usuário.
     */
	private static void incluirTime(MrBetSistema mb, Scanner scanner) {
		System.out.print("\nCódigo: ");
		String codigo = scanner.nextLine();
		
		System.out.print("\nNome: ");
		String nome = scanner.nextLine();
		
		System.out.print("\nMascote: ");
		String mascote = scanner.nextLine();
				
		System.out.println(mb.incluiTime(codigo, nome, mascote));
	}
	
    /**
     * Recupera e exibe as informações de um time com base no seu código.
     * 
     * @param mb O objeto que representa o sistema MrBet.
     * @param scanner Objeto Scanner para ler a entrada do usuário.
     */
	private static void recuperarTime(MrBetSistema mb, Scanner scanner) {
		System.out.print("\nCódigo: ");
		String codigo = scanner.nextLine();
		System.out.println(mb.recuperaTime(codigo));
	}
	
    /**
     * Adiciona um campeonato ao sistema, solicitando ao usuário o nome e número de participantes.
     * 
     * @param mb O objeto que representa o sistema MrBet.
     * @param scanner Objeto Scanner para ler a entrada do usuário.
     */
	private static void adicionarCampeonato(MrBetSistema mb, Scanner scanner) {	
		System.out.print("\nNome: ");
		String nome = scanner.nextLine();
		System.out.print("\nParticipantes: ");
		int qtdParticipantes = scanner.nextInt();
		scanner.nextLine();
		System.out.println(mb.adicionaCampeonato(nome, qtdParticipantes));
	}
	
    /**
     * Inclui um time em um campeonato.
     * Solicita ao usuário o código do time e o nome do campeonato.
     * 
     * @param mb O objeto que representa o sistema MrBet.
     * @param scanner Objeto Scanner para ler a entrada do usuário.
     */
	private static void incluirTimeEmCampeonato(MrBetSistema mb, Scanner scanner) {	
		System.out.print("\nCódigo: ");
		String codigo = scanner.nextLine();
		System.out.print("\nCampeonato: ");
		String campeonato = scanner.nextLine();		
		System.out.println(mb.incluiTimeEmCampeonato(codigo, campeonato));
	}
	
    /**
     * Verifica se um time está inscrito em um campeonato.
     * Solicita ao usuário o código do time e o nome do campeonato.
     * 
     * @param mb O objeto que representa o sistema MrBet.
     * @param scanner Objeto Scanner para ler a entrada do usuário.
     */
	private static void verificarTimeEmCampeonato(MrBetSistema mb, Scanner scanner) {
		System.out.print("\nCódigo: ");
		String codigo = scanner.nextLine();
		System.out.print("\nCampeonato: ");
		String campeonato = scanner.nextLine();	
		System.out.println(mb.verificaTimeEmCampeonato(codigo, campeonato));
	}
	
    /**
     * Exibe todos os campeonatos em que um time está participando.
     * Solicita ao usuário o código do time.
     * 
     * @param mb O objeto que representa o sistema MrBet.
     * @param scanner Objeto Scanner para ler a entrada do usuário.
     */
	private static void exibirCampeonatosTime(MrBetSistema mb, Scanner scanner) {
		System.out.print("\nTime: ");
		String codigo = scanner.nextLine();
		System.out.println(mb.exibeCampeonatosTime(codigo));
	}
	
    /**
     * Exibe o submenu para registrar uma aposta ou exibir o status das apostas.
     * 
     * @param mb O objeto que representa o sistema MrBet.
     * @param scanner Objeto Scanner para ler a entrada do usuário.
     */
	private static void apostarOuStatus(MrBetSistema mb, Scanner scanner) {
		System.out.println("\n(A) Apostar ou (S) Status das Apostas?");
        String opcao2 = scanner.nextLine().trim().toUpperCase();
        if (opcao2.equals("A")) {
            apostar(mb, scanner);    
        } else if (opcao2.equals("S")) {
            statusApostas(mb);
        } else {
        	System.out.println("OPÇÃO INVÁLIDA!");	
        }
	}
	
    /**
     * Permite ao usuário registrar uma aposta.
     * Solicita ao usuário o código do time, o nome do campeonato, a colocação e o valor da aposta.
     * 
     * @param mb O objeto que representa o sistema MrBet.
     * @param scanner Objeto Scanner para ler a entrada do usuário.
     */
	private static void apostar(MrBetSistema mb, Scanner scanner) {
		System.out.print("\nCódigo: ");
		String codigo = scanner.nextLine();
		
		System.out.print("\nCampeonato: ");
		String campeonato = scanner.nextLine();
		
		System.out.print("\nColocação: ");
		int colocacao = scanner.nextInt();
		
		System.out.print("\nValor da aposta: R$");
		double valor = scanner.nextDouble();
		scanner.nextLine();
		
		System.out.println(mb.aposta(codigo, campeonato, colocacao, valor));
	}
	
    /**
     * Exibe o status de todas as apostas registradas no sistema.
     * 
     * @param mb O objeto que representa o sistema MrBet.
     */
	private static void statusApostas(MrBetSistema mb) {
		System.out.println(mb.statusApostas());
	}
	
    /**
     * Encerra o programa com uma mensagem final.
     */
	private static void sai() {
		System.out.println("That's all, folks!");
		System.exit(0);
	}
}

