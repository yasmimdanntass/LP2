package testesControllers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import complementacao.Usuario;
import controllers.GeradorRelatorio;
import controllers.UsuarioController;

class TestesUsuarioController {
	
	private UsuarioController uc;
	
	@BeforeEach
	void setUp() throws Exception {
		uc = new UsuarioController();
	}

	// TESTES CRIAÇÃO DE USUÁRIOS
	
	@Test
	void testCriaEstudante() {
		uc.criaEstudante("Misa Amane", "123.456.789-00", "12345678", "124110543");
		assertTrue(uc.verificaSenhaUsuario("123.456.789-00", "12345678"));
	}
	
	@Test
	void testCriaEstudanteCpfDuplicado() {
		uc.criaEstudante("Julia", "231.324.900-00", "12345678", "124110543");
		assertFalse(uc.criaEstudante("a", "231.324.900-00", "12345678", "124110543"));
	}
	
	@Test
	void testCriaEstudanteSenhaInvalida() {
	    Exception e = assertThrows(IllegalArgumentException.class, () -> {
	        uc.criaEstudante("Amanda", "123.456.789-10", "123", "124110543");
	    });
	    assertEquals("SENHA COM TAMANHO INVÁLIDO!", e.getMessage());
	}
	
	@Test
	void testCriaEstudanteCpfInvalido() {
	    Exception e = assertThrows(IllegalArgumentException.class, () -> {
	        uc.criaEstudante("Chappell", "12345678910", "12345678", "124110543");
	    });
	    assertEquals("CPF COM TAMANHO INVÁLIDO!", e.getMessage());
	}
	
	@Test
	void testCriaEstudanteNomeVazio() {
	    Exception e = assertThrows(IllegalArgumentException.class, () -> {
	        uc.criaEstudante("   ", "123.456.789-10", "12345678", "124110543");
	    });
	    assertEquals("ENTRADA VAZIA!", e.getMessage());
	}
	
	@Test
	void testCriaEstudanteMatriculaNula() {
	    Exception e = assertThrows(NullPointerException.class, () -> {
	        uc.criaEstudante("Joana", "123.456.789-10", "12345678", null);
	    });
	    assertEquals("ENTRADA NULA!", e.getMessage());
	}

	
	// TESTES LISTAGEM 
	
	@Test
	public void testListagemEstudantesVazia() {
	    String[] resultado = uc.listaUsuariosPorNome();
	    assertEquals(0, resultado.length);
	}
	
	@Test
	public void testListagemEstudantesOrdemAlfabetica() {
	    uc.criaEstudante("Carlos", "111.111.111-11", "aaaa1111", "2023003");
	    uc.criaEstudante("Ana", "222.222.222-22", "bbbb2222", "2023001");
	    uc.criaEstudante("Beatriz", "333.333.333-33", "cccc3333", "2023002");

	    String[] resultado = uc.listaUsuariosPorNome();

	    assertEquals("Nome: Ana | Matricula: 2023001 | Pontuação total: 0", resultado[0]);
	    assertEquals("Nome: Beatriz | Matricula: 2023002 | Pontuação total: 0", resultado[1]);
	    assertEquals("Nome: Carlos | Matricula: 2023003 | Pontuação total: 0", resultado[2]);
	}
	
	@Test
	public void testListagemEstudantesOrdemAlfabeticaMaiusculasMinusculas() {
	    uc.criaEstudante("carlos", "111.111.111-11", "aaaa1111", "2023003");
	    uc.criaEstudante("Ana", "222.222.222-22", "bbbb2222", "2023001");
	    uc.criaEstudante("beatriz", "333.333.333-33", "cccc3333", "2023002");

	    String[] resultado = uc.listaUsuariosPorNome();

	    assertEquals("Nome: Ana | Matricula: 2023001 | Pontuação total: 0", resultado[0]);
	    assertEquals("Nome: beatriz | Matricula: 2023002 | Pontuação total: 0", resultado[1]);
	    assertEquals("Nome: carlos | Matricula: 2023003 | Pontuação total: 0", resultado[2]);
	}
	
	@Test
	public void testListagemEstudantesNomesIguais() {
	    uc.criaEstudante("Ana", "111.111.111-11", "aaaa1111", "2023001");
	    uc.criaEstudante("Ana", "222.222.222-22", "bbbb2222", "2023002");

	    String[] resultado = uc.listaUsuariosPorNome();

	    assertEquals(2, resultado.length);
	    assertEquals("Nome: Ana | Matricula: 2023001 | Pontuação total: 0", resultado[0]);
	    assertEquals("Nome: Ana | Matricula: 2023002 | Pontuação total: 0", resultado[1]);
	}

	// TESTES VERIFICA SENHA
	
	@Test
	public void testVerificaSenhaCorreta() {
	    uc.criaEstudante("Annika", "123.456.789-00", "senha123", "2023001");
	    assertTrue(uc.verificaSenhaUsuario("123.456.789-00", "senha123"));
	}

	@Test
	public void testVerificaSenhaErrada() {
	    uc.criaEstudante("Barbie", "123.456.789-00", "senha123", "2023001");
	    assertFalse(uc.verificaSenhaUsuario("123.456.789-00", "outra321"));
	}
	
	@Test
	public void testVerificaSenhaCpfNaoCadastrado() {
	    assertThrows(IllegalArgumentException.class, ()-> uc.verificaSenhaUsuario("123.456.789-00", "outra321"));
	}
	
	// TESTES ALTERA SENHA
	
	@Test
	public void testAlteraSenhaComAutenticacaoValida() {
	    uc.criaEstudante("Misa Amane", "123.456.789-00", "senha123", "2023001");

	    boolean alterado = uc.alteraSenhaEstudante("123.456.789-00", "senha123", "nova1234");

	    assertTrue(alterado);
	}
	
	public void testAlteraSenhaComDadosCorretos() {
	    uc.criaEstudante("Elsa", "123.456.789-00", "senha123", "2023001");
	    boolean sucesso = uc.alteraSenhaEstudante("123.456.789-00", "senha123", "nova4321");
	    assertTrue(sucesso);
	}
	
	@Test
	public void testAlteraSenhaComSenhaIncorreta() {
	    uc.criaEstudante("Anna", "123.456.789-00", "senha123", "2023001");
	    boolean sucesso = uc.alteraSenhaEstudante("123.456.789-00", "errada123", "nova4321");
	    assertFalse(sucesso);
	}
	
	@Test
	public void testAlteraSenhaComTamanhoInvalido() {
	    uc.criaEstudante("L", "123.456.789-00", "senha123", "2023001");

	    Exception e = assertThrows(IllegalArgumentException.class, () -> {
	        uc.alteraSenhaEstudante("123.456.789-00", "senha123", "curta");
	    });

	    assertEquals("SENHA COM TAMANHO INVÁLIDO!", e.getMessage());
	}
	
	@Test
	public void testAlteraSenhaComCpfInexistente() {
	    boolean sucesso = uc.alteraSenhaEstudante("000.000.000-00", "senha123", "nova1234");
	    assertFalse(sucesso);
	}
	
	@Test
	public void testAlteraSenhaComCpfVazio() {
	    Exception e = assertThrows(IllegalArgumentException.class, () -> {
	        uc.alteraSenhaEstudante("", "senha123", "nova1234");
	    });

	    assertEquals("ENTRADA VAZIA!", e.getMessage());
	}
	
	@Test
	public void testSenhaRealmenteAlterada() {
	    uc.criaEstudante("Near", "123.456.789-00", "senha123", "2023001");

	    boolean alterado = uc.alteraSenhaEstudante("123.456.789-00", "senha123", "nova1234");
	    assertTrue(alterado);

	    assertTrue(uc.verificaSenhaUsuario("123.456.789-00", "nova1234"));
	    assertFalse(uc.verificaSenhaUsuario("123.456.789-00", "senha123"));
	}

	@Test
	public void testSenhaNaoAlteradaSeAntigaIncorreta() {
	    uc.criaEstudante("Light", "111.222.333-44", "abc12345", "2023002");

	    boolean alterado = uc.alteraSenhaEstudante("111.222.333-44", "errada123", "nova1234");
	    assertFalse(alterado);

	    assertTrue(uc.verificaSenhaUsuario("111.222.333-44", "abc12345")); 
	    assertFalse(uc.verificaSenhaUsuario("111.222.333-44", "nova1234")); 
	}
	
	// TESTES ADICIONA PONTOS DICA
	
	@Test
	public void testAdicionaPontosDica() {
	    uc.criaEstudante("Maomao", "111.222.333-44", "remedio1", "2023001");

	    boolean sucesso = uc.adicionaPontosDica("111.222.333-44", 40);
	    assertTrue(sucesso);

	    Usuario u = uc.getUsuario("111.222.333-44", "remedio1");
	    assertEquals(40, u.getPontosDicas());
	}
	
	@Test
	public void testAdicionaPontosDicaAcumulado() {
	    uc.criaEstudante("Jinshi", "222.333.444-55", "maomao12", "2023002");

	    uc.adicionaPontosDica("222.333.444-55", 25);
	    uc.adicionaPontosDica("222.333.444-55", 35);

	    Usuario u = uc.getUsuario("222.333.444-55", "maomao12");
	    assertEquals(60, u.getPontosDicas());
	}

	@Test
	public void testAdicionaPontosDicaCpfInvalido() {
	    boolean sucesso = uc.adicionaPontosDica("000.000.000-00", 10);
	    assertFalse(sucesso);
	}
	
	@Test
	public void testAdicionaPontosDicaValorNegativo() {
	    uc.criaEstudante("Elsa", "345.678.901-22", "gelo1234", "2023003");

	    assertThrows(IllegalArgumentException.class, () -> {
	        uc.adicionaPontosDica("345.678.901-22", -10);
	    });
	}
	
	@Test
	public void testAdicionaPontosDicaCpfNulo() {
	    assertThrows(NullPointerException.class, () -> {
	        uc.adicionaPontosDica(null, 10);
	    });
	}
	
	// TESTES RANKING POR DICA
	
	@Test
	public void testRankingUsuariosPorDicas() {
	    uc.criaEstudante("A", "111.111.111-11", "senha111", "M1");
	    uc.criaEstudante("B", "222.222.222-22", "senha222", "M2");
	    uc.criaEstudante("C", "333.333.333-33", "senha333", "M3");

	    uc.adicionaPontosDica("111.111.111-11", 5);
	    uc.adicionaPontosDica("222.222.222-22", 15);
	    uc.adicionaPontosDica("333.333.333-33", 10);

	    String[] ranking = uc.listaUsuariosRankingDicas();

	    assertTrue(ranking[0].contains("B")); 
	    assertTrue(ranking[1].contains("C")); 
	    assertTrue(ranking[2].contains("A"));
	}
	
	@Test
	public void testRankingUmUsuario() {
	    uc.criaEstudante("A", "111.111.111-11", "senha111", "M1");
	    uc.adicionaPontosDica("111.111.111-11", 30);

	    String[] ranking = uc.listaUsuariosRankingDicas();

	    assertEquals(1, ranking.length);
	    assertTrue(ranking[0].contains("A"));
	}

	@Test
	public void testRankingVazio() {
	    String[] ranking = uc.listaUsuariosRankingDicas();
	    assertEquals(0, ranking.length);
	}

	
	// TESTES LISTA HISTORICO
	
	@Test
	public void testHistoricoInicialmenteVazio() {
	    uc.criaEstudante("A", "111.111.111-11", "senha123", "M1");
	    String historico = uc.listaHistorico("111.111.111-11", "senha123");
	    assertTrue(historico.isEmpty());
	}

	@Test
	public void testHistoricoComUmRelatorio() {
	    uc.criaEstudante("B", "222.222.222-22", "senha123", "M2");
	    Usuario usuario = uc.getUsuario("222.222.222-22", "senha123");
	    GeradorRelatorio gr = new GeradorRelatorio();

	    gr.geraRelatorioParcial(usuario, true);
	    String historico = uc.listaHistorico("222.222.222-22", "senha123");

	    assertFalse(historico.isEmpty());
	    assertTrue(historico.contains(LocalDate.now().toString()));
	}
	
	@Test
	public void testHistoricoComMultiplosRelatorios() {
	    uc.criaEstudante("C", "333.333.333-33", "senha123", "M3");
	    Usuario usuario = uc.getUsuario("333.333.333-33", "senha123");
	    usuario.adicionaRelatorio("sla", "blabla");
	    assertTrue(usuario.listaHistorico().contains("sla\nblabla"));;
	}

	
	// TESTES EXCLUI ITEM HISTORICO
	
	@Test
	public void testExcluiItemHistorico() {
	    uc.criaEstudante("Anna", "123.456.789-00", "senha123", "2023001");
	    Usuario u = uc.getUsuario("123.456.789-00", "senha123");
	    u.adicionaRelatorio("2025-04-08", "relatorioblabla");

	    boolean excluido = uc.excluiItemHistorico("123.456.789-00", "senha123", "2025-04-08");
	    assertTrue(excluido);
	}
	
	@Test
	public void testExcluiItemHistoricoDataInexistente() {
	    uc.criaEstudante("Elsa", "234.567.890-00", "gelado12", "2023002");
	    Usuario u = uc.getUsuario("234.567.890-00", "gelado12");
	    u.adicionaRelatorio("2025-04-08", "relatorio qualquer");

	    boolean excluido = uc.excluiItemHistorico("234.567.890-00", "gelado12", "2024-01-01");
	    assertFalse(excluido);
	}
	
	@Test
	public void testExcluiItemHistoricoComMultiplos() {
	    uc.criaEstudante("Anna", "123.456.789-00", "senha123", "2023001");
	    Usuario u = uc.getUsuario("123.456.789-00", "senha123");
	    u.adicionaRelatorio("2025-04-08", "relatorio A");
	    u.adicionaRelatorio("2025-04-09", "relatorio B");

	    boolean excluido = uc.excluiItemHistorico("123.456.789-00", "senha123", "2025-04-08");
	    String historico = uc.listaHistorico("123.456.789-00", "senha123");

	    assertTrue(excluido);
	    assertFalse(historico.contains("2025-04-08"));
	    assertTrue(historico.contains("2025-04-09"));
	}
	
	@Test
	public void testExcluiItemHistoricoSenhaIncorreta() {
	    uc.criaEstudante("Elsa", "234.567.890-00", "gelado12", "2023002");
	    Usuario u = uc.getUsuario("234.567.890-00", "gelado12");
	    u.adicionaRelatorio("2025-04-08", "relatorio secreto");

	    assertThrows(IllegalArgumentException.class, ()-> uc.excluiItemHistorico("234.567.890-00", "errada", "2025-04-08"));
	}

	@Test
	public void testExcluiItemHistoricoCpfInvalido() {
	    assertThrows(IllegalArgumentException.class, ()-> uc.excluiItemHistorico("000.000.000-00", "qualquer", "2025-04-08"));
	}





	


	
	
	

}
