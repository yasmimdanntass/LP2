package lp2.lab01;
public class IdadePreferencialInteligente {
    public static void main(String[] args) { // Definindo uma função
                                             //
        int atual = 2024;
        int nascimento = 2005;
        int idade = atual - nascimento;
        boolean gravida = true;
        boolean colo = false;
        int x = 0;

        if (idade >= 60) {
            System.out.println("Voce tem " + idade
                    + " anos. Voce pode usar o atendimento especial.");
        } else {

            if (gravida == true){
                System.out.println("Preferencial Gestante");

            } else if (colo == true){
                System.out.println("Preferencial pois está com " + x + " crianças de colo.");

            } else {
            
                System.out.println("Voce tem " + idade + " anos. Voce ainda nao pode usar o atendimento especial.");
            } 
        }
    }
}

