package br.edu.ufcg.computacao.mrbet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApostaTests {

	@Test
	void testToString() {
		Campeonato camp = new Campeonato("Camp1", 2);
		Time time = new Time("ABBA", "ABBA", "MAMMA MIA");
		Aposta nova = new Aposta(time, camp, 2, 40);
		assertEquals("[ABBA] ABBA/MAMMA MIA\nCamp1\n2/2\nR$ 40,00\n", nova.toString());
	}
	
	@Test
	void testEquals() {
		Campeonato camp = new Campeonato("Camp1", 2);
		Time time = new Time("ABBA", "ABBA", "MAMMA MIA");
		Time time2 = new Time("ABBA", "ABBA", "MAMMA MIA");
		assertEquals(time, time2);
	}
	
	@Test
	void testNotEquals() {
		Campeonato camp = new Campeonato("Camp1", 2);
		Time time = new Time("ABBA", "ABBA", "MAMMA MIA");
		Time time2 = new Time("ATLÉTICO ABBA", "ABBA", "MAMMA MIA");
		assertNotEquals(time, time2);
	}
	
}
