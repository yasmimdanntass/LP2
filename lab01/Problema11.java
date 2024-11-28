/**
 * Laboratório de Programação 2 - Lab 1
 * 
 * @author Yasmim Dantas da Costa Souza - 124110564
 */

import java.util.Scanner;

public class Problema11{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int chosen = sc.nextInt(), choice = -20;

        while (choice != chosen){
            
            choice = sc.nextInt();

            if (choice > chosen){
                System.out.println("MAIOR");
            } else if (choice < chosen) {
                System.out.println("MENOR");
            }
        }

        System.out.println("ACERTOU");

    }
}





