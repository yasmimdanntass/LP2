/**
 * Laboratório de Programação 2 - Lab 1
 * 
 * @author Yasmim Dantas da Costa Souza - 124110564
 *
 */

import java.util.Scanner;

public class Problema10{
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        String[] meses = sc.nextLine().split(" ");
        String[] valores = sc.nextLine().split(" ");
    
        String entrada = sc.nextLine();
        
        for (int i = 0; i < meses.length; i++){
            
            if (meses[i].equals(entrada)){
                System.out.println(valores[i]);
                break;
            }
        }

        sc.close();
    }
}

    

