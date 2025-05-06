package br.edu.ufcg.computacao.mrbet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.mrbet.Campeonato;
import br.edu.ufcg.computacao.mrbet.Time;

class TimeTests {

	@Test
	void testAdicionaCampeonato() {
		Time t = new Time("ABBA", "ABBA", "MAMMA MIA");
		Campeonato c = new Campeonato("Camp1", 2);
		t.adicionaCampeonato(c);
		assertTrue(t.getCampeonatosParticipante().contains(c));
	}
	
	@Test
	void testAdicionaCampeonatoRepetido() {
		Time t = new Time("ABBA", "ABBA", "MAMMA MIA");
		Campeonato c = new Campeonato("Camp1", 2);
		t.adicionaCampeonato(c);
		t.adicionaCampeonato(c);
		assertTrue(t.getCampeonatosParticipante().contains(c));
		assertTrue(t.getCampeonatosParticipante().size() == 1);
	}
	
	@Test
	void testExibeCampeonatosParticipante() {
		Time t = new Time("ABBA", "ABBA", "MAMMA MIA");
		Campeonato c = new Campeonato("Camp1", 2);
		t.adicionaCampeonato(c);
		String[] campeonatosEsperados = {
	            "* Camp1 - 0/2"};
		assertArrayEquals(t.exibeCampeonatos(), campeonatosEsperados);
	}
	
	@Test
	void testToString() {
		Time t = new Time("ABBA", "ABBA", "MAMMA MIA");
		assertEquals("[ABBA] ABBA / MAMMA MIA", t.toString());
	}

}
