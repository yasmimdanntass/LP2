/**
 * Laboratório de Programação 2 - Lab 1
 * 
 * @author Yasmim Dantas da Costa Souza - 124110564
 */

import java.util.Scanner;

public class Problema9 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int ideal = sc.nextInt(), newValue = sc.nextInt(), aprox, newHeight, newAprox;
        aprox = Math.abs(ideal - newValue);

        while (aprox != 0){

            newHeight = sc.nextInt();
            newAprox = Math.abs(newHeight - ideal);

            if (newAprox < aprox && newAprox != 0){
                System.out.println("ADEQUADO");

            } else if (newAprox >= aprox && newAprox != 0){
                System.out.println("PERIGO");
            }

            aprox = newAprox;
        }

        System.out.println("OK"); 

    }
}






