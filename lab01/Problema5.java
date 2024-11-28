/**
 * Laboratório de Programação 2 - Lab1
 *
 * @author Yasmim Dantas da Costa Souza - 124110564
 */
import java.util.Scanner;

public class Problema5{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		String op = sc.nextLine();

		if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")){
			
			float num1 = sc.nextFloat();
			float num2 = sc.nextFloat();
			float result = 0;
			
			if (op.equals("/") && num2 == 0){
				System.out.println("ERRO");

			} else {
		        
				if (op.equals("+")){
					result = num1 + num2;
				} else if (op.equals("-")){
					result = num1 - num2;
				} else if (op.equals("*")){
					result = num1 * num2;
				} else {
					result = num1 / num2;
				}

				System.out.println("RESULTADO: " + result);

			}

		} else {
			System.out.println("ENTRADA INVALIDA");

		}

	}

}

