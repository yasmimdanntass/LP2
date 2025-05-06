package elementos;

/**
 * Interface que representa um elemento de uma dica.
 * 
 * Os elementos podem ser textos, mídias ou referências, e cada um
 * deve ser capaz de fornecer uma descrição resumida, uma descrição
 * detalhada e sua pontuação associada.
 * @author Yasmim Dantas
 */
public interface Elemento {
    
    /**
     * Retorna uma descrição resumida do elemento.
     * 
     * @return a descrição resumida
     */
    public String getDescricaoResumida();
    
    /**
     * Retorna uma descrição detalhada do elemento.
     * 
     * @return a descrição detalhada
     */
    public String getDescricaoDetalhada();
    
    /**
     * Retorna a pontuação atribuída ao elemento.
     * 
     * @return a pontuação
     */
    public int getPontuacao();
    
}

