/*
 * Laboratório de Programação 2 - Lab 1
 *
 * @author Yasmim Dantas da Costa Souza - 124110564
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Problema8{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> lista = new ArrayList<>();
		int soma = 0, menor = 0, maior = 0;
		int media = 0;
		String entrada;
			
		while (!(entrada = sc.nextLine()).equals("-")) {
			String[] palavras = entrada.split(" ");
			int valor = Integer.parseInt(palavras[1]);
			lista.add(valor);
			soma += valor; 
		} 

		media = soma / lista.size(); 
			
		for (int inteiro : lista) {
	
			if (inteiro >= 700){
				maior += 1;
			} else if (inteiro < 700){
				menor += 1;
			}
		}

		System.out.println("maior: " + Collections.max(lista));
		System.out.println("menor: " + Collections.min(lista));
		System.out.println("media: " + media);
		System.out.println("acima: " + maior);
		System.out.println("abaixo: " + menor);
	}
}

		

			


			


			


