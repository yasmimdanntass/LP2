/**
 * Laboratório de Programação 2 - Lab 1
 * 
 * @author Yasmim Dantas da Costa Souza - 124110564
 */

import java.util.Scanner;

public class Problema12{
    
    public static int myMax(int[] array){
	int pos = -1;
	int max = array[0];

        for (int i = 0; i < array.length; i++){
            if (array[i] >= max){
		pos = i;
		max = array[i];
	    }
	   
        }
        return pos;
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String[] values = sc.nextLine().split(" ");
        int[] arrayInt = new int[values.length];
	int max1 = 0, max2 = 0;

        for (int i = 0; i < values.length; i++){
            arrayInt[i] = Integer.parseInt(values[i]);
	}

	max1 = arrayInt[myMax(arrayInt)];
	arrayInt[myMax(arrayInt)] = 0;
	max2 = arrayInt[myMax(arrayInt)];

	System.out.println(max1 + max2);
    }
}






        

            



            
            
