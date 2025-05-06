package filmnow;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilmeTestes {
	
	private Filme filme;
	private Filme filme2;
	private Filme filme3;
	private Filme filme4;
	
	@BeforeEach
	void setUp() {
		filme = new Filme("Us", "2019", "Netflix");
		filme2 = new Filme("Us", "2019", "Prime Video");
		filme3 = new Filme("Anora", "2024", "Cinema");
		filme4 = new Filme("Anora", "", "Cinema");
	}

	@Test
	void testFilmeTodosOsParametros() {
		assertDoesNotThrow(() -> new Filme("Branca de Neve e os Sete Anões", "1937", "Disney+"));
	}
	
	@Test
	void testFilmeSemNome() {
		assertThrows(IllegalArgumentException.class, () -> new Filme("  ", "1937", "Disney+"));
	}
	
	@Test
	void testFilmeNomeNulo() {
		assertThrows(NullPointerException.class, () -> new Filme(null, "1937", "Disney+"));
	}
	
	@Test
	void testFilmeSemLocal() {
		assertThrows(IllegalArgumentException.class, () -> new Filme("Branca de Neve e os Sete Anões", "1937", "  "));
	}
	
	@Test
	void testFilmeLocalNulo() {
		assertThrows(NullPointerException.class, () -> new Filme("Branca de Neve e os Sete Anões", "1937", null));
	}
	
	@Test
	void testHashCode() {
		assertEquals(filme.hashCode(), filme2.hashCode());

	}
	
	@Test
	void testHashCodeDiferente() {
		assertNotEquals(filme3.hashCode(), filme4.hashCode());

	}

	@Test
	void testSetAno() {
		filme.setAno("2020");
		assertEquals(filme.getAno(), "2020");
	}

	@Test
	void testSetLocal() {
		filme.setLocal("Prime Video");
		assertEquals(filme.getLocal(), "Prime Video");
	}

	@Test
	void testSetNome() {
		filme.setNome("Doutor Sono");
		assertEquals(filme.getNome(), "Doutor Sono");
	}

	@Test
	void testSetHot() {
		assertFalse(filme.getHot());
		filme.setHot(true);
		assertTrue(filme.getHot());
	}

	@Test
	void testEqualsFilmesIguais() {
		assertEquals(filme, filme2);
	}
	
	@Test
	void testEqualsFilmesDiferentes() {
		assertNotEquals(filme, filme3);
	}
	
	@Test
	void testToStringComAno() {
		assertEquals("Us, 2019", filme.toString());
	}
	
	@Test
	void testToStringSemAno() {
		assertEquals("Anora", filme4.toString());
	}
}
