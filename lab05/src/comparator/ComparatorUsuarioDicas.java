package comparator;

import java.util.Comparator;
import complementacao.Usuario;

/**
 * ComparatorUsuarioDicas é um comparador que ordena objetos da classe Usuario
 * com base na quantidade de pontos de dicas acumulados.
 * 
 * A ordenação é feita de forma decrescente, ou seja, usuários com mais pontos
 * aparecem antes dos que têm menos.
 * 
 * Esse comparador é útil, por exemplo, para gerar rankings de usuários por participação.
 *  
 * @author Yasmim Dantas
 */
public class ComparatorUsuarioDicas implements Comparator<Usuario> {

    /**
     * Compara dois usuários com base nos seus pontos de dicas.
     * 
     * @param u1 o primeiro usuário
     * @param u2 o segundo usuário
     * @return valor negativo se u2 tiver mais pontos que u1,
     *         0 se forem iguais,
     *         valor positivo se u1 tiver mais pontos que u2
     */
    @Override
    public int compare(Usuario u1, Usuario u2) {
        return Integer.compare(u2.getPontosDicas(), u1.getPontosDicas());
    }
}
