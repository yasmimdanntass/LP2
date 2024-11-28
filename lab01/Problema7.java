/*
 * Laboratório de Programação 2 - Lab 1
 *
 * @author Yasmim Dantas da Costa Souza - 124110564
 */
import java.util.Scanner;

public class Problema7{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
	String entrada, output;
	int marcador = 0;
	entrada = sc.nextLine();

	while (!entrada.equals("wally")){
		output = "?";
		for (String palavra : entrada.split(" ")){
			if (palavra.length() == 5){
				output = palavra;
			}
		}
	
		System.out.println(output);
		
		entrada = sc.nextLine();

	} 
    }
}




