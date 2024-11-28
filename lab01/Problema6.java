/*
 * Laboratório de Programação 2 - Lab 1
 *
 * @author Yasmim Dantas da Costa Souza - 124110564
 */
import java.util.Scanner;

public class Problema6 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int qtd = 0, soma = 0;
        String entrada = sc.nextLine(), saida;

        for (String num : entrada.split(" ")) {
            int valor = Integer.parseInt(num);
            soma = soma + valor;
            qtd = qtd + 1;
        }

        double media = soma / qtd;
        saida = "";

        for (String num : entrada.split(" ")) {
            int valor = Integer.parseInt(num);
            if (valor > media){
                saida = saida + Integer.toString(valor) + " ";

            }
        }

        System.out.println(saida.substring(0, saida.length()-1));

    }
}




            


