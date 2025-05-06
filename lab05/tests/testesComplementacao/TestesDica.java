package testesComplementacao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import complementacao.Dica;
import complementacao.Usuario;

class TestesDica {

	private Usuario usuario;

    @BeforeEach
    public void setup() {
        usuario = new Usuario("Fulano", "123.456.789-00", "12345678", "mat");
    }
	
    // TESTES DE CONSTRUTOR 
   
    @Test
    public void testHappyDay() {
        Dica dica = new Dica(usuario, "blablabla");
        assertNotNull(dica);
        assertEquals("blablabla", dica.getTema());
        assertEquals(usuario, dica.getUsuario());
    }

    @Test
    public void testTemaNull() {
        assertThrows(NullPointerException.class, () -> {
            new Dica(usuario, null);
        });
    }

    @Test
    public void testTemaVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Dica(usuario, "   ");
        });
    }

    @Test
    public void testUsuarioNull() {
        assertThrows(NullPointerException.class, () -> {
            new Dica(null, "lalala");
        });
    }

    // TESTES ADICIONANDO ELEMENTOS
    
    @Test
    public void testAdicionaElementoTexto() {
        Dica dica = new Dica(usuario, "lalala");
        dica.adicionaElementoTexto("exemplo de texto", 5);
        assertTrue(dica.getDicaResumida().contains("exemplo de texto"));
    }

    @Test
    public void testAdicionaElementoMultimidia() {
        Dica dica = new Dica(usuario, "midia");
        dica.adicionaElementoMultimidia("link", "cabeçalho", 10, 3);
        String resultado = dica.getDicaResumida();
        assertTrue(resultado.contains("link"));
        assertTrue(resultado.contains("cabeçalho"));
    }

    @Test
    public void testAdicionaElementoReferencia() {
        Dica dica = new Dica(usuario, "referencias");
        dica.adicionaElementoReferencia("Título", "Fonte", "link", 2022, true, 4, 3);
        String resultado = dica.getDicaResumida();
        assertTrue(resultado.contains("Título"));
        assertTrue(resultado.contains("Fonte"));
    }
    
    public void testAdicionaElementoReferenciaRelevanciaInvalida() {
        Dica dica = new Dica(usuario, "referencias");
        dica.adicionaElementoReferencia("Título", "Fonte", "link", 2022, true, 4, 3);
        String resultado = dica.getDicaResumida();
        assertTrue(resultado.contains("Título"));
        assertTrue(resultado.contains("Fonte"));
    }

    @Test
    public void testGetUsuario() {
        Dica dica = new Dica(usuario, "teste");
        assertEquals(usuario, dica.getUsuario());
    }

    @Test
    public void testTextoEntradaInvalida() {
        Dica dica = new Dica(usuario, "tema");
        assertThrows(IllegalArgumentException.class, () -> {
            dica.adicionaElementoTexto("   ", 1);
        });
    }

    @Test
    public void testReferenciaComAnoInvalido() {
        Dica dica = new Dica(usuario, "tema");
        assertThrows(IllegalArgumentException.class, () -> {
            dica.adicionaElementoReferencia("titulo", "fonte", "link", -1, false, 3, 2);
        });
    }

    @Test
    public void testReferenciaComImportanciaForaDoIntervalo() {
        Dica dica = new Dica(usuario, "tema");
        assertThrows(IllegalArgumentException.class, () -> {
            dica.adicionaElementoReferencia("titulo", "fonte", "link", 2022, false, 6, 2);
        });
    }

    @Test
    public void testGetDicaDetalhadaContemTudo() {
        Dica dica = new Dica(usuario, "blablabla");
        dica.adicionaElementoTexto("texto", 1);
        dica.adicionaElementoMultimidia("link.com", "cabecalho", 5, 1);
        dica.adicionaElementoReferencia("ref", "fonte", "link", 2021, true, 2, 1);

        String detalhada = dica.getDicaDetalhada();
        assertTrue(detalhada.contains("texto"));
        assertTrue(detalhada.contains("cabecalho"));
        assertTrue(detalhada.contains("ref"));
    }
}

