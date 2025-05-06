package testesControllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import complementacao.Usuario;
import controllers.AtividadeController;

class TestesAtividadeController {

	AtividadeController ac;
    Usuario u1;

    @BeforeEach
    void setUp() {
        ac = new AtividadeController();
        u1 = new Usuario("Ana", "123.456.789-00", "senha123", "matricula");
    }

    // TESTES DE CRIAÇÃO
    	
    @Test
    void testCriarPesquisaExtensaoComCreditosTruncados() {
        String codigo = ac.criaAtividadePesquisaExtensaoEmUsuario(u1, 38, "projeto");
        assertEquals("123.456.789-00_1", codigo);
        assertEquals(3, ac.creditosAtividade(u1, "PESQUISA_EXTENSAO"));
    }
    
    @Test
    void testCriarPesquisaExtensaoComCreditosAcimaDoLimite() {
        String codigo = ac.criaAtividadePesquisaExtensaoEmUsuario(u1, 1500, "IA");
        assertEquals("123.456.789-00_1", codigo);
        assertEquals(18, ac.creditosAtividade(u1, "PESQUISA_EXTENSAO"));
    }

    @Test
    void testCriarPesquisaExtensaoComSubtipoInvalido() {
    	assertEquals("ATIVIDADE NÃO CADASTRADA", ac.criaAtividadePesquisaExtensaoEmUsuario(u1, 5, ""));
    }
    
    @Test
    void testCriarRepresentacaoComSubtipoInvalido() {
        assertEquals("ATIVIDADE NÃO CADASTRADA", ac.criaAtividadeRepresentacaoEstudantilEmUsuario(u1, 5, ""));
    }
    
    @Test
    void testCriarRepresentacaoComTempoInsuficiente() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            ac.criaAtividadeRepresentacaoEstudantilEmUsuario(u1, 0, "DCE");
        });
        assertEquals("REPRESENTAÇÃO ESTUDANTIL COM MENOS TEMPO QUE O MÍNIMO NECESSÁRIO!", e.getMessage());
    }
    
    @Test
    void testCriarMonitoriaComLimiteDeCreditos() {
        String codigo = ac.criaAtividadeMonitoriaEmUsuario(u1, 10, "POO");
        assertEquals("123.456.789-00_1", codigo);
        assertEquals(16, ac.creditosAtividade(u1, "MONITORIA"));
    }	

    @Test
    void testCriarEstagioValido() {
        String codigo = ac.criaAtividadeEstagioEmUsuario(u1, 360, "Empresa ABC");
        assertEquals("123.456.789-00_1", codigo);
        assertEquals(6, ac.creditosAtividade(u1, "ESTAGIO"));
    }
    
    @Test
    void testCriarEstagioComCreditosAcimaDoLimite() {
        String codigo = ac.criaAtividadeEstagioEmUsuario(u1, 1500, "Empresa Muito Boa");
        assertEquals("123.456.789-00_1", codigo);
        assertEquals(18, ac.creditosAtividade(u1, "ESTAGIO"));
    }

    @Test
    void testCriarEstagioComMenosDe300Horas() {
        assertEquals("ATIVIDADE NÃO CADASTRADA", ac.criaAtividadeEstagioEmUsuario(u1, 250, "Empresa XYZ"));
    }
    
    @Test
    void testCriarRepresentacaoComCreditosLimitados() {
        String codigo = ac.criaAtividadeRepresentacaoEstudantilEmUsuario(u1, 5, "DCE");
        assertEquals("123.456.789-00_1", codigo);
        assertEquals(2, ac.creditosAtividade(u1, "REPRESENTACAO_ESTUDANTIL")); 
    }
    
    @Test
    void testCodigosGeradosSaoSequenciaisParaMesmoUsuario() {
        String c1 = ac.criaAtividadeEstagioEmUsuario(u1, 360, "Empresa A");
        String c2 = ac.criaAtividadeEstagioEmUsuario(u1, 360, "Empresa B");
        assertEquals("123.456.789-00_1", c1);
        assertEquals("123.456.789-00_2", c2);
    }

    @Test
    void testCodigoSequencialParaUsuariosDiferentes() {
        Usuario u = new Usuario("Bruno", "124.543.234-00", "senha321", "matricula");
        String c1 = ac.criaAtividadeMonitoriaEmUsuario(u1, 3, "LP");
        String c2 = ac.criaAtividadeMonitoriaEmUsuario(u, 3, "LP");
        assertEquals("123.456.789-00_1", c1);
        assertEquals("124.543.234-00_1", c2);
    }
    
    // TESTES ALTERANDO DESCRIÇÃO E COMPROVAÇÃO
    
    @Test
    void testDescricaoAlteradaComSucesso() {
        String codigo = ac.criaAtividadeEstagioEmUsuario(u1, 360, "Empresa XYZ");
        boolean resultado = ac.alteraDescricaoAtividade(u1, codigo, "novo estágio");
        assertTrue(resultado);
    }

    @Test
    void testAlterarDescricaoAtividadeInexistente() {
        boolean resultado = ac.alteraDescricaoAtividade(u1, "124.543.234-00-9", "desc");
        assertFalse(resultado);
    }

    @Test
    void testAlterarComprovacaoAtividade() {
        String codigo = ac.criaAtividadeMonitoriaEmUsuario(u1, 3, "EDB");
        boolean resultado = ac.alteraComprovacaoAtividade(u1, codigo, "link.com");
        assertTrue(resultado);
    }

    @Test
    void testAlterarComprovacaoAtividadeInexistente() {
        boolean resultado = ac.alteraComprovacaoAtividade(u1, "124.543.234-00-5", "link.com");
        assertFalse(resultado);
    }

    // TESTES DE MAPA DE CRÉDITOS
    
    @Test
    void testMapaCreditosComTodasAtividades() {
        ac.criaAtividadeEstagioEmUsuario(u1, 360, "Empresa");
        ac.criaAtividadeMonitoriaEmUsuario(u1, 2, "AED");
        ac.criaAtividadePesquisaExtensaoEmUsuario(u1, 36, "Iniciação Científica");
        ac.criaAtividadeRepresentacaoEstudantilEmUsuario(u1, 5, "Centro Acadêmico");

        String esperado = "PESQUISA_EXTENSAO - 3\n"
                        + "MONITORIA - 8\n"
                        + "ESTAGIO - 6\n"
                        + "REPRESENTACAO_ESTUDANTIL - 2\n";

        String mapa = ac.geraMapaCreditosAtividades(u1);
        assertEquals(esperado, mapa);
    }
    
    @Test
    void testMapaCreditosSemAtividades() {
        String esperado = "PESQUISA_EXTENSAO - 0\n"
                        + "MONITORIA - 0\n"
                        + "ESTAGIO - 0\n"
                        + "REPRESENTACAO_ESTUDANTIL - 0\n";

        String mapa = ac.geraMapaCreditosAtividades(u1);
        assertEquals(esperado, mapa);
    }
    
    @Test
    void testMapaCreditosComAcumulacao() {
        ac.criaAtividadeMonitoriaEmUsuario(u1, 1, "AED");
        ac.criaAtividadeMonitoriaEmUsuario(u1, 2, "POO");

        String esperado = "PESQUISA_EXTENSAO - 0\n"
                        + "MONITORIA - 12\n"
                        + "ESTAGIO - 0\n"
                        + "REPRESENTACAO_ESTUDANTIL - 0\n";

        String mapa = ac.geraMapaCreditosAtividades(u1);
        assertEquals(esperado, mapa);
    }

    // TESTES VERIFICANDO META	

    @Test
    void testVerificaMetaAlcancada() {
        ac.criaAtividadeEstagioEmUsuario(u1, 1080, "Empresa");
        ac.criaAtividadePesquisaExtensaoEmUsuario(u1, 48, "extensão"); 
        assertTrue(ac.verificaMetaAlcancada(u1)); 
    }

    @Test
    void testVerificaMetaNaoAlcancada() {
        ac.criaAtividadeMonitoriaEmUsuario(u1, 3, "Calculo");
        assertFalse(ac.verificaMetaAlcancada(u1)); 
    }
    
    // TESTES DE CRÉDITOS
    
    @Test
    void testCreditosParaTipoInexistente() {
        assertEquals(0, ac.creditosAtividade(u1, "ESTAGIO"));
    }
    
    @Test
    void testCreditosComTipoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            ac.creditosAtividade(u1, "INVALIDO");
        });
    }
    
    @Test
    void testCreditosZeroParaTipoValidoSemAtividades() {
        assertEquals(0, ac.creditosAtividade(u1, "REPRESENTACAO_ESTUDANTIL"));
    }

}
