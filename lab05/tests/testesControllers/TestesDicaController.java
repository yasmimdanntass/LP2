package testesControllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import complementacao.Usuario;
import controllers.DicaController;

class TestesDicaController {
	private Usuario u1;
    private Usuario u2;
    private DicaController dc;
    
	@BeforeEach
	void setUp() throws Exception {
		u1 = new Usuario("Anna", "111.111.111-11", "senha123", "MAT1");
        u2 = new Usuario("Elsa", "222.222.222-22", "senha456", "MAT2");
        dc = new DicaController();
	}

	// TESTES ADICIONANDO DICAS
	
	@Test
    public void testAdicionarElementoTexto() {
        int posicao = dc.adicionaDica(u1, "POO");
        String texto = "blablabla ".repeat(20); 

        boolean adicionado = dc.adicionaElementoTextoDica(u1, posicao, texto);
        assertTrue(adicionado);
        assertTrue(u1.getPontosDicas() > 0);
    }

    @Test
    public void testAdicionarElementoMultimidia() {
        int posicao = dc.adicionaDica(u1, "Algoritmos");
        boolean adicionado = dc.adicionaElementoMultimidiaDica(u1, posicao, "https://link.com", "Cabeçalho", 300);
        assertTrue(adicionado);
        assertEquals(50, u1.getPontosDicas());
    }

    @Test
    public void testAdicionarElementoReferencia() {
        int posicao = dc.adicionaDica(u1, "Banco de Dados");
        boolean adicionado = dc.adicionaElementoReferenciaDica(u1, posicao, "Título", "Fonte", 2023, true, 5);
        assertTrue(adicionado);
        assertEquals(15, u1.getPontosDicas());
    }
    
    @Test
    public void testPontuacaoMaximaTexto() {
        int posicao = dc.adicionaDica(u1, "Estrutura de Dados");
        
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < 600; i++) {
            texto.append("a");
        }
        
        dc.adicionaElementoTextoDica(u1, posicao, texto.toString());
        
        assertEquals(50, u1.getPontosDicas());
    }

    
    // TESTES DE LISTAGEM

    @Test
    public void testListarDicasSimples() {
        dc.adicionaDica(u1, "Threads");
        dc.adicionaDica(u1, "Sincronização");

        String[] listagem = dc.listaDicas();
        assertEquals(2, listagem.length);
        assertEquals("Autor: Anna\n", listagem[0]);
    }

    @Test
    public void testListarDicasDetalhadas() {
        int posicao = dc.adicionaDica(u1, "Compiladores");
        dc.adicionaElementoTextoDica(u1, posicao, "lalala");

        String[] listagemDetalhada = dc.listaDicasDetalhes();
        assertEquals(1, listagemDetalhada.length);
        assertTrue(listagemDetalhada[0].contains("lalala"));
    }

    @Test
    public void testListarDicaPorPosicao() {
        int posicao = dc.adicionaDica(u1, "IA");
        String dica = dc.listaDica(posicao);
        String[] listagem = dc.listaDicas();
        assertEquals("Autor: Anna\n", listagem[0]);
    }

    @Test
    public void testListarDicaDetalhadaPorPosicao() {
        int posicao = dc.adicionaDica(u1, "Teoria da Computação");
        dc.adicionaElementoTextoDica(u1, posicao, "conteúdo");

        String detalhada = dc.listaDicaDetalhes(posicao);
        assertTrue(detalhada.contains("conteúdo"));
    }
    
    @Test
    public void testListarDicaComPosicaoNegativa() {
        Exception e = assertThrows(IllegalArgumentException.class, () ->
            dc.listaDica(-1)
        );
        assertEquals("POSIÇÃO NÃO PREENCHIDA OU NÃO EXISTENTE!", e.getMessage());
    }

    @Test
    public void testListarDicaComPosicaoForaDoLimite() {
        dc.adicionaDica(u1, "Sistemas Operacionais");

        Exception e = assertThrows(IllegalArgumentException.class, () ->
            dc.listaDica(5)
        );
        assertEquals("POSIÇÃO NÃO PREENCHIDA OU NÃO EXISTENTE!", e.getMessage());
    }

    @Test
    public void testListarDicaDetalhadaComPosicaoNegativa() {
        Exception e = assertThrows(IllegalArgumentException.class, () ->
            dc.listaDicaDetalhes(-1)
        );
        assertEquals("POSIÇÃO NÃO PREENCHIDA OU NÃO EXISTENTE!", e.getMessage());
    }

    @Test
    public void testListarDicaDetalhadaComPosicaoForaDoLimite() {
        dc.adicionaDica(u1, "Redes");

        Exception e = assertThrows(IllegalArgumentException.class, () ->
            dc.listaDicaDetalhes(10)
        );
        assertEquals("POSIÇÃO NÃO PREENCHIDA OU NÃO EXISTENTE!", e.getMessage());
    }
    
    
    // TESTES DE FALHAS AO ADICIONAR ELEMENTOS

    @Test
    public void testAdicionarTextoComUsuarioErrado() {
        int posicao = dc.adicionaDica(u1, "Programação Concorrente");

        Exception e = assertThrows(IllegalArgumentException.class, () ->
            dc.adicionaElementoTextoDica(u2, posicao, "texto qualquer")
        );

        assertEquals("O USUÁRIO NÃO É AUTOR DESSA DICA!", e.getMessage());
    }

    @Test
    public void testAdicionarMultimidiaComUsuarioErrado() {
        int posicao = dc.adicionaDica(u1, "Redes");

        Exception e = assertThrows(IllegalArgumentException.class, () ->
            dc.adicionaElementoMultimidiaDica(u2, posicao, "link", "header", 60)
        );

        assertEquals("O USUÁRIO NÃO É AUTOR DESSA DICA!", e.getMessage());
    }

    @Test
    public void testAdicionarReferenciaComUsuarioErrado() {
        int posicao = dc.adicionaDica(u1, "Criptografia");

        Exception e = assertThrows(IllegalArgumentException.class, () ->
            dc.adicionaElementoReferenciaDica(u2, posicao, "titulo", "fonte", 2024, true, 3)
        );

        assertEquals("O USUÁRIO NÃO É AUTOR DESSA DICA!", e.getMessage());
    }

    @Test
    public void testRelevanciaForaDoIntervalo() {
        int posicao = dc.adicionaDica(u1, "Engenharia de Software");

        Exception e = assertThrows(IllegalArgumentException.class, () ->
            dc.adicionaElementoReferenciaDica(u1, posicao, "t", "f", 2024, true, 6)
        );

        assertEquals("RELEVÂNCIA FORA DO INTERVALO PERMITIDO!", e.getMessage());
    }
    
    @Test
    public void testAdicionarTextoComPosicaoInvalida() {
        Exception e = assertThrows(IllegalArgumentException.class, () ->
            dc.adicionaElementoTextoDica(u1, 3, "blablabla")
        );
        assertEquals("POSIÇÃO NÃO PREENCHIDA OU NÃO EXISTENTE!", e.getMessage());
    }
    
    @Test
    public void testAdicionarMultimidiaComPosicaoInvalida() {
        Exception e = assertThrows(IllegalArgumentException.class, () ->
            dc.adicionaElementoMultimidiaDica(u1, 2, "link", "cabecalho", 60)
        );
        assertEquals("POSIÇÃO NÃO PREENCHIDA OU NÃO EXISTENTE!", e.getMessage());
    }
    
    @Test
    public void testAdicionarReferenciaComPosicaoInvalida() {
        Exception e = assertThrows(IllegalArgumentException.class, () ->
            dc.adicionaElementoReferenciaDica(u1, 5, "titulo", "fonte", 2020, true, 4)
        );
        assertEquals("POSIÇÃO NÃO PREENCHIDA OU NÃO EXISTENTE!", e.getMessage());
    }

}

