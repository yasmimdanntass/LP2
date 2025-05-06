package testesControllers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividades.Atividade;
import complementacao.Usuario;
import controllers.AtividadeController;
import controllers.GeradorRelatorio;

public class TestesGeradorRelatorio {
	private Usuario u1;
	private Usuario u2;
    private AtividadeController ac;
    private GeradorRelatorio gr;

    @BeforeEach
    public void setUp() {
        u1 = new Usuario("a", "123.234.345-00", "12345678", "mat");
        u2 = new Usuario("b", "000.000.000-00", "12345678", "mat");
        ac = new AtividadeController();
        ac.criaAtividadeEstagioEmUsuario(u1, 1000, "blabla");
        ac.criaAtividadeMonitoriaEmUsuario(u1, 500, "blabla");
        ac.criaAtividadePesquisaExtensaoEmUsuario(u1, 50, "blabla");
        gr = new GeradorRelatorio();
    }
    
    // TESTES GERANDO RELATORIO FINAL
    @Test
    void testGeraRelatorioFinalUsuarioNull() {
    	assertThrows(NullPointerException.class, ()-> gr.geraRelatorioFinal(null));
    }
    
    @Test
    void testGeraRelatorioFinalUsuarioNaoAtingiuAMeta() {
    	assertThrows(IllegalArgumentException.class, ()-> gr.geraRelatorioFinal(u2));
    }
    
    @Test
    void testGeraRelatorioFinalUsuarioAtingiuAMeta() {
    	assertEquals(("a, 123.234.345-00, mat\n"
    			+ "PESQUISA_EXTENSAO: 4/18\n"
    			+ "MONITORIA: 16/16\n"
    			+ "ESTAGIO: 16/18\n"
    			+ "REPRESENTACAO_ESTUDANTIL: 0/2\n"
    			+ "TOTAL: 22"), gr.geraRelatorioFinal(u1));
    }
    
    // TESTES RELATORIO FINAL POR ATIVIDADE
    
    @Test
    void testGeraRelatorioFinalPorAtividadeUsuarioNull() {
    	assertThrows(NullPointerException.class, ()-> gr.geraRelatorioFinalPorAtividade(null, "ESTAGIO"));
    }
    
    @Test
    void testGeraRelatorioFinalPorAtividadeTipoVazio() {
    	assertThrows(IllegalArgumentException.class, ()-> gr.geraRelatorioFinalPorAtividade(u1, ""));
    }
    
    @Test
    void testGeraRelatorioFinalPorAtividadeTipoNulo() {
    	assertThrows(NullPointerException.class, ()-> gr.geraRelatorioFinalPorAtividade(u1, null));
    }
    
    @Test
    void testGeraRelatorioFinalPorAtividadeNaoAtingiuMeta() {
    	assertThrows(IllegalArgumentException.class, ()-> gr.geraRelatorioFinalPorAtividade(u2, "ESTAGIO"));
    }
    
    @Test
    void testGeraRelatorioFinalPorAtividade() {
    	assertEquals(("a, 123.234.345-00, mat\n"
    			+ "ESTAGIO: 16/18\n"), gr.geraRelatorioFinalPorAtividade(u1, "ESTAGIO"));
    	assertEquals(("a, 123.234.345-00, mat\n"
    			+ "MONITORIA: 16/16\n"), gr.geraRelatorioFinalPorAtividade(u1, "MONITORIA"));
    	
    }
    
   // TESTES DE RELATÓRIO PARCIAL 
    
    @Test
    void testGeraRelatorioParcialPorAtividadeUsuarioNull() {
    	assertThrows(NullPointerException.class, ()-> gr.geraRelatorioParcialPorAtividade(null, "bla", false));
    }
    
    @Test
    void testGeraRelatorioParcialPorAtividadeTipoVazio() {
    	assertThrows(IllegalArgumentException.class, ()-> gr.geraRelatorioParcialPorAtividade(u1, "", false));
    }
    
    @Test
    void testGeraRelatorioParcialPorAtividadeTipoNulo() {
    	assertThrows(NullPointerException.class, ()-> gr.geraRelatorioParcialPorAtividade(u1, null, false));
    }
    
    @Test
    void testRelatorioParcialSemSalvar() {

        GeradorRelatorio gr = new GeradorRelatorio();

        String relatorio = gr.geraRelatorioParcial(u1, false);
        String esperado = "a, 123.234.345-00, mat\n"
        		+ "PESQUISA_EXTENSAO: 4/18\n"
        		+ "MONITORIA: 16/16\n"
        		+ "ESTAGIO: 16/18\n"
        		+ "REPRESENTACAO_ESTUDANTIL: 0/2\n";

        assertEquals(esperado, relatorio);
        assertEquals("", u1.listaHistorico());
    }

    @Test
    void testRelatorioParcialComSalvar() {

        GeradorRelatorio gr = new GeradorRelatorio();

        String relatorio = gr.geraRelatorioParcial(u1, true);
        String hoje = LocalDate.now().toString();

        String esperadoRelatorio = "a, 123.234.345-00, mat\n"
        		+ "PESQUISA_EXTENSAO: 4/18\n"
        		+ "MONITORIA: 16/16\n"
        		+ "ESTAGIO: 16/18\n"
        		+ "REPRESENTACAO_ESTUDANTIL: 0/2\n";

        assertEquals(esperadoRelatorio, relatorio);
        assertEquals(hoje + "\n" + esperadoRelatorio + "\n", u1.listaHistorico());
    }

    @Test
    void testRelatorioParcialNaoSalvaQuandoFalse() {
        Usuario u1 = new Usuario("Dalva", "000.111.222-33", "senha123", "20230004");
        GeradorRelatorio gr = new GeradorRelatorio();

        gr.geraRelatorioParcial(u1, false);
        assertEquals("", u1.listaHistorico());
    }

    @Test
    void testRelatorioParcialSemAtividades() {
        Usuario u1 = new Usuario("Joana", "555.666.777-88", "senha456", "20230005");
        GeradorRelatorio gr = new GeradorRelatorio();

        String relatorio = gr.geraRelatorioParcial(u1, false);
        String esperado = "Joana, 555.666.777-88, 20230005\n" +
                "PESQUISA_EXTENSAO: 0/18\n" +
                "MONITORIA: 0/16\n" +
                "ESTAGIO: 0/18\n" +
                "REPRESENTACAO_ESTUDANTIL: 0/2\n";

        assertEquals(esperado, relatorio);
    }

    // TESTES DE RELATÓRIO PARCIAL POR ATIVIDADE
    
    @Test
    void testRelatorioParcialPorAtividadeSemSalvar() {
        String esperado = "a, 123.234.345-00, mat\n"
        		+ "MONITORIA: 16/16\n";

        String relatorio = gr.geraRelatorioParcialPorAtividade(u1, "MONITORIA", false);
        assertEquals(esperado, relatorio);
        assertEquals("", u1.listaHistorico());
    }

    @Test
    void testRelatorioParcialPorAtividadeComSalvar() {

        String esperado = "a, 123.234.345-00, mat\n"
        		+ "REPRESENTACAO_ESTUDANTIL: 0/2\n";

        String relatorio = gr.geraRelatorioParcialPorAtividade(u1, "REPRESENTACAO_ESTUDANTIL", true);
        assertEquals(esperado, relatorio);

        String esperadoHistorico = LocalDate.now() + "\n" + esperado + "\n";
        assertEquals(esperadoHistorico, u1.listaHistorico());
    }

    @Test
    void testRelatorioParcialPorAtividadeSemCreditos() {
        Usuario u = new Usuario("João", "111.222.333-44", "segredos", "20231234");

        String esperado = """
            João, 111.222.333-44, 20231234
            ESTAGIO: 0/18
            """;

        String relatorio = gr.geraRelatorioParcialPorAtividade(u, "ESTAGIO", false);
        assertEquals(esperado, relatorio);
        assertEquals("", u.listaHistorico());
    }

    @Test
    void testRelatorioParcialPorAtividadeSalvaHistMesmoDia() {

        String esperado = "a, 123.234.345-00, mat\n"
        		+ "MONITORIA: 16/16";
        
        gr.geraRelatorioParcialPorAtividade(u1, "ESTAGIO", true);
        gr.geraRelatorioParcialPorAtividade(u1, "MONITORIA", true);
        
        String data = LocalDate.now().toString();
        String esperadoHistorico = data + "\n" + esperado + "\n\n";
        assertEquals(esperadoHistorico, u1.listaHistorico());
    }

}