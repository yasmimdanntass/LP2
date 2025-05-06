package br.edu.ufcg.computacao.mrbet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.mrbet.MrBetSistema;

class MrBetSistemaTests {
	private MrBetSistema mb;
	
	@BeforeEach
	void setUp() throws Exception {
		this.mb = new MrBetSistema();
		mb.incluiTime("250_PB", "Nacional de Patos", "Canário");
		mb.incluiTime("252_PB", "Sport Lagoa Seca", "Carneiro");
		mb.incluiTime("002_RJ", "Clube de Regatas do Flamengo", "Urubu");
		mb.incluiTime("105_PB", "Sport Lagoa Seca", "Carneiro");
		
	}

	@Test
	void testIncluiTime() {
		assertEquals("INCLUSÃO REALIZADA", mb.incluiTime("250_JP", "KyotoTops", "Sakura"));
	}
	
	@Test
	void testIncluiTimeJaExistente() {
		assertEquals("TIME JÁ EXISTE!", mb.incluiTime("250_PB", "Nacional de Patos", "Canário"));
	}
	
	@Test
	void testIncluiTimeNull() {
		assertThrows(IllegalArgumentException.class, ()-> mb.incluiTime(null, null, null));
	}
	
	@Test
	void testIncluiTimeVazio() {
		assertThrows(IllegalArgumentException.class, ()-> mb.incluiTime(" ", " ", " "));
	}
	
	@Test
	void testRecuperaTime() {
		assertEquals("[250_PB] Nacional de Patos / Canário", mb.recuperaTime("250_PB"));
	}
	
	@Test
	void testRecuperaTimeNaoExiste() {
		assertEquals("TIME NÃO EXISTE!", mb.recuperaTime("250_AM"));
	}
	
	@Test
	void testRecuperaTimeNull() {
		assertThrows(IllegalArgumentException.class, ()-> mb.recuperaTime(null));
	}
	
	@Test
	void testRecuperaTimeVazio() {
		assertThrows(IllegalArgumentException.class, ()-> mb.recuperaTime(" "));
	}
	
	@Test
	void testAdicionaCampeonato() {
		assertEquals("CAMPEONATO ADICIONADO!", mb.adicionaCampeonato("Brasileirão série A 2023", 1));
	}
	
	@Test
	void testAdicionaCampeonatoJaExistente() {
		mb.adicionaCampeonato("Brasileirão série A 2023", 1);
		assertEquals("CAMPEONATO JÁ EXISTE!", mb.adicionaCampeonato("Brasileirão série A 2023", 1));
	}
	
	@Test
	void testAdicionaCampeonatoNull() {
		assertThrows(IllegalArgumentException.class, ()-> mb.adicionaCampeonato(null, 0));
	}
	
	@Test
	void testAdicionaCampeonatoVazio() {
		assertThrows(IllegalArgumentException.class, ()-> mb.adicionaCampeonato(" ", 0));
	}
	
	@Test
	void testAdicionaCampeonatoInvalido() {
		assertThrows(IllegalArgumentException.class, ()-> mb.adicionaCampeonato("Azul", -1));
	}
	
	@Test
	void testIncluiTimeEmCampeonato() {
		mb.adicionaCampeonato("Brasileirão série A 2023", 1);
		assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mb.incluiTimeEmCampeonato("250_PB", "Brasileirão série A 2023"));
	}
	
	@Test
	void testIncluiTimeEmCampeonatoNull() {
		mb.adicionaCampeonato("Brasileirão série A 2023", 1);
		assertThrows(IllegalArgumentException.class, ()-> mb.incluiTimeEmCampeonato(null, "Brasileirão série A 2023"));
	}
	
	@Test
	void testIncluiTimeEmCampeonatoVazio() {
		mb.adicionaCampeonato("Brasileirão série A 2023", 1);
		assertThrows(IllegalArgumentException.class, ()-> mb.incluiTimeEmCampeonato(" ", "Brasileirão série A 2023"));
	}
	
	@Test
	void testIncluiTimeEmCampeonatoJaIncluido() {
		mb.adicionaCampeonato("Brasileirão série A 2023", 2);
		assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mb.incluiTimeEmCampeonato("250_PB", "Brasileirão série A 2023"));
		assertEquals("TIME INCLUÍDO NO CAMPEONATO!", mb.incluiTimeEmCampeonato("250_PB", "Brasileirão série A 2023"));
		
	}
	
	@Test
	void testIncluiTimeEmCampeonatoTimeNaoExiste() {
		mb.adicionaCampeonato("Brasileirão série A 2023", 1);
		assertEquals("O TIME NÃO EXISTE!", mb.incluiTimeEmCampeonato("250_JP", "Brasileirão série A 2023"));
	}
	
	@Test
	void testIncluiTimeEmCampeonatoCampeonatoNaoExiste() {
		mb.adicionaCampeonato("Brasileirão série A 2023", 1);
		assertEquals("O CAMPEONATO NÃO EXISTE!", mb.incluiTimeEmCampeonato("250_PB", "Brasileirão série B 2023"));
	}
	
	@Test
	void testIncluiTimeEmCampeonatoCampeonatoLotado() {
		mb.adicionaCampeonato("ValorantGame", 1);
		mb.incluiTimeEmCampeonato("250_PB", "ValorantGame");
		assertEquals("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!", mb.incluiTimeEmCampeonato("250_PB", "ValorantGame"));
	
	}
	
	@Test
	void testVerificaTimeEmCampeonato() {
		mb.adicionaCampeonato("ValorantGame", 1);
		mb.incluiTimeEmCampeonato("250_PB", "ValorantGame");
		assertEquals("O TIME ESTÁ NO CAMPEONATO!", mb.verificaTimeEmCampeonato("250_PB", "ValorantGame"));
	}
	
	@Test
	void testVerificaTimeEmCampeonatoNaoEsta() {
		mb.adicionaCampeonato("ValorantGame", 1);
		assertEquals("O TIME NÃO ESTÁ NO CAMPEONATO!", mb.verificaTimeEmCampeonato("250_PB", "ValorantGame"));
	}
	
	@Test
	void testVerificaTimeEmCampeonatoNull() {
		mb.adicionaCampeonato("ValorantGame", 1);
		assertThrows(IllegalArgumentException.class, ()-> mb.verificaTimeEmCampeonato(null, "ValorantGame"));
	}
	
	@Test
	void testVerificaTimeEmCampeonatoCampeonatoNaoExiste() {
		assertEquals("O CAMPEONATO NÃO EXISTE!", mb.verificaTimeEmCampeonato("250_PB", "ValorantGame"));
	}
	
	
	@Test
	void testVerificaTimeEmCampeonatoVazio() {
		mb.adicionaCampeonato("ValorantGame", 1);
		assertThrows(IllegalArgumentException.class, ()-> mb.verificaTimeEmCampeonato(" ", "ValorantGame"));
	}

	@Test
	void testVerificaTimeEmCampeonatoTimeNaoExiste() {
		assertEquals("O TIME NÃO EXISTE!", mb.verificaTimeEmCampeonato("256_PB", "ValorantGame"));
	}
	
	@Test
	void testExibeCampeonatosTime() {
		mb.adicionaCampeonato("ValorantGame", 1);
		mb.incluiTimeEmCampeonato("250_PB", "ValorantGame");
		assertEquals("* ValorantGame - 1/1\n", mb.exibeCampeonatosTime("250_PB"));
	}
	
	@Test
	void testExibeCampeonatosTimeNull() {
		assertThrows(IllegalArgumentException.class, ()-> mb.exibeCampeonatosTime(null));
	}
	
	@Test
	void testExibeCampeonatosTimeVazio() {
		assertThrows(IllegalArgumentException.class, ()-> mb.exibeCampeonatosTime(" "));
	}
	
	@Test
	void testExibeCampeonatosTimeNaoExiste0() {
		assertEquals("O TIME NÃO EXISTE!", mb.exibeCampeonatosTime("a"));
	}
	
	@Test
	void testAposta() {
		mb.adicionaCampeonato("ValorantGame", 1);
		mb.incluiTimeEmCampeonato("250_PB", "ValorantGame");
		assertEquals("APOSTA REGISTRADA!", mb.aposta("250_PB", "ValorantGame", 1, 40));
	}
	
	@Test
	void testApostaColocacaoMenorZero() {
		mb.adicionaCampeonato("ValorantGame", 1);
		mb.incluiTimeEmCampeonato("250_PB", "ValorantGame");
		assertThrows(IllegalArgumentException.class, ()-> mb.aposta("250_PB", "ValorantGame", -1, 40));
	}
	
	@Test
	void testApostaNull() {
		assertThrows(IllegalArgumentException.class, ()-> mb.aposta(null, "ValorantGame", 2, 20));
	}
	
	@Test
	void testApostaVazio() {
		assertThrows(IllegalArgumentException.class, ()-> mb.aposta(" ", "ValorantGame", 2, 20));
	}
	
	@Test
	void testApostaTimeNaoExiste() {
		mb.adicionaCampeonato("ValorantGame", 1);
		assertEquals("O TIME NÃO EXISTE!", mb.aposta("259_PB", "ValorantGame", 1, 40));
	}
	
	@Test
	void testApostaCampeonatoNaoExiste() {
		assertEquals("O CAMPEONATO NÃO EXISTE!", mb.aposta("250_PB", "ValorantGame", 1, 40));
	}
	
	@Test
	void testApostaTimeNaoEstaNoCampeonato() {
		mb.adicionaCampeonato("ValorantGame", 1);
		assertEquals("O TIME NÃO ESTÁ NO CAMPEONATO", mb.aposta("250_PB", "ValorantGame", 1, 40));
	}
	
	@Test
	void testApostaColocacaoInvalida() {
		mb.adicionaCampeonato("ValorantGame", 1);
		mb.incluiTimeEmCampeonato("250_PB", "ValorantGame");
		assertEquals("APOSTA NÃO REGISTRADA!", mb.aposta("250_PB", "ValorantGame", 2, 40));
	}
	
	@Test
	void testStatusApostasComAposta() {
		mb.adicionaCampeonato("ValorantGame", 1);
		mb.incluiTimeEmCampeonato("250_PB", "ValorantGame");
		assertEquals("APOSTA REGISTRADA!", mb.aposta("250_PB", "ValorantGame", 1, 40));
		assertEquals("Apostas: \n \n1. [250_PB] Nacional de Patos/Canário\nValorantGame\n1/1\nR$ 40,00\n", mb.statusApostas());						
	}
	
	@Test
	void testStatusApostasSemAposta() {
		assertEquals("Apostas: \n \n", mb.statusApostas());
	}
}
