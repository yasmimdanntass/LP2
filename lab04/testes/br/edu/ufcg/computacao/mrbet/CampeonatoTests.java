package br.edu.ufcg.computacao.mrbet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufcg.computacao.mrbet.Campeonato;
import br.edu.ufcg.computacao.mrbet.Time;

class CampeonatoTests {
	
	@Test
	void testCampAdicionaTime() {
		Campeonato camp = new Campeonato("Camp1", 2);
		Time time = new Time("ABBA", "ABBA", "MAMMA MIA");
		camp.adicionaTime(time);
		assertTrue(camp.getTimesParticipantes().contains(time));
	}
	
	@Test
	void testVerificaTime() {
		Campeonato camp = new Campeonato("Camp1", 2);
		Time time = new Time("ABBA", "ABBA", "MAMMA MIA");
		camp.adicionaTime(time);
		assertTrue(camp.verificaTime(time));
	}
	
	@Test
	void testVerificaTimeSemTime() {
		Campeonato camp = new Campeonato("Camp1", 2);
		Time time = new Time("ABBA", "ABBA", "MAMMA MIA");
		assertFalse(camp.verificaTime(time));
	}
	
	@Test
	void testVerificaPodeAdicionarMaisTime() {
		Campeonato camp = new Campeonato("Camp1", 2);
		Time time = new Time("ABBA", "ABBA", "MAMMA MIA");
		Time time2 = new Time("ABBA2", "ABBA", "MAMMA MIA");
		camp.adicionaTime(time);
		camp.adicionaTime(time2);
		assertFalse(camp.verificaPodeAdicionarMaisTime());
	}
	
	@Test
	void testToString() {
		Campeonato c = new Campeonato("ABBA", 1);
		assertEquals("ABBA - 0/1", c.toString());
	}
}

