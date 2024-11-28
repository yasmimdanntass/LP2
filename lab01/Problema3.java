/**
 * Laboratório de Programação 2 - Lab1
 *
 * @author Yasmim Dantas da Costa Souza - 124110564
 */
import java.util.Scanner;

public class Problema3{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		float num1 = sc.nextFloat();
		float num2 = sc.nextFloat();
		float mean = (num1 + num2) / 2;

		String output = "False!";

		if (mean >= 7){
			output  = "True!";
		}

		System.out.println("pass: " + output);
		

	}
}

