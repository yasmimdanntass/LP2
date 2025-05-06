package filmnow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilmNowTestes {


    private Filme filmeBase;
    private Filme filme2;
    private FilmNow fn;


    @BeforeEach
    void preparaFilmes() {
    	
        this.filmeBase = new Filme("Avatar", "2009", "Disney+");
        this.filme2 = new Filme("20 dias em Mariupol", "2023", "Cinema");
        this.fn = new FilmNow(); 
    }

    @Test
    void testNomeAno() {
       assertEquals("Avatar, 2009", this.filmeBase.toString());
    }
   
    @Test
    void testAdicionaFilmePosicaoVazia() {
       String resultado = fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
       assertEquals("FILME ADICIONADO", resultado);
    }
    
    @Test
    void testAdicionaFilme2PosicaoOcupada() {
       fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
       String resultado = fn.cadastraFilme(1, "20 dias em Mariupol", "2023", "Cinema");
       assert(fn.getFilme(1).equals(this.filme2));
       assertEquals("FILME ADICIONADO", resultado);
       
    }
    
    @Test
    void testAdicionaFilmeDuasVezes() {
        fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
        String resultado = fn.cadastraFilme(3, "Avatar", "2009", "Disney+");
        assert(fn.getFilme(1).equals(this.filmeBase));
        assert(fn.getFilme(3) == null);
        assertEquals("FILME JÁ ADICIONADO", resultado);
        
     }
    
    @Test
    void testAdicionaFilmeDuasVezesLocalDiferente() {
        fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
        String resultado = fn.cadastraFilme(3, "Avatar", "2009", "Amazon Prime");
        assert(fn.getFilme(1).equals(this.filmeBase));
        assert(fn.getFilme(3) == null);
        assertEquals("FILME JÁ ADICIONADO", resultado);
        
     }
    
    @Test
    void testAdicionaFilmePosicaoLimite() {
        String resultado = fn.cadastraFilme(100, "Avatar", "2009", "Disney+");
        assertEquals("FILME ADICIONADO", resultado);
        
     }
    
    @Test
    void testAdicionaFilmePosicaoAlemLimite() {
        String resultado = fn.cadastraFilme(101, "Avatar", "2009", "Disney+");
        assertEquals("POSIÇÃO INVÁLIDA", resultado);
        
     }
    
    @Test
    void testAdicionaFilmePosicaoAquemLimite() {
        String resultado = fn.cadastraFilme(0, "Avatar", "2009", "Disney+");
        assertEquals("POSIÇÃO INVÁLIDA", resultado);
        
     }
    
    @Test
    void testAdicionaFilmeLocalVazio() {
        String resultado = fn.cadastraFilme(1, "Avatar", "2009", "");
        assertEquals("FILME INVÁLIDO", resultado);
        
     }
    
    @Test
    void testAdicionaFilmeAnoVazio() {
        String resultado = fn.cadastraFilme(1, "Avatar", "", "Disney+");
        assertEquals("FILME ADICIONADO", resultado);
        
     }
    
    @Test
    void testAdicionaFilmeNomeVazio() {
        String resultado = fn.cadastraFilme(1, "", "2009", "Disney+");
        assertEquals("FILME INVÁLIDO", resultado);
        
     }
    
    @Test
    void testDetalhaFilme() {
    	fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
    	String resultado = fn.detalhaFilme(1);
    	assertEquals("Avatar, 2009\nDisney+", resultado);
    }
    
    @Test
    void testDetalhaFilmeSemAno() {
    	fn.cadastraFilme(1, "Avatar", "", "Disney+");
    	String resultado = fn.detalhaFilme(1);
    	assertEquals("Avatar\nDisney+", resultado);
    }
    
    @Test
    void testDetalhaFilmeEmPosicaoSemFilme() {
    	String resultado = fn.detalhaFilme(1);
    	assertEquals("", resultado);
    }
    
    @Test
    void testDetalhaFilmeEmPosicaoAquemDoLimite() {
    	String resultado = fn.detalhaFilme(0);
    	assertEquals("POSIÇÃO INVÁLIDA", resultado);
    }
    
    @Test
    void testDetalhaFilmeEmPosicaoAlemDoLimite() {
    	String resultado = fn.detalhaFilme(101);
    	assertEquals("POSIÇÃO INVÁLIDA", resultado);
    }
    
    @Test
    void testExibeFilmes() {
    	fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
    	String resultado = fn.exibeFilmes();
    	assertEquals("1 - Avatar, 2009\n", resultado);
    }
    
    @Test
    void testExibeFilmesSemFilme() {
    	String resultado = fn.exibeFilmes();
    	assertEquals("", resultado);
    }
    @Test
    void testAdicionaFilmeNaHotList() {
    	fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
    	String resultado = fn.adicionaFilmeHotList(1, 1);
    	assertEquals("FILME ADICIONADO NA HOTLIST", resultado);
    }
    
    @Test
    void testAdicionaFilmeNaHotListSubstituindoOutro() {
    	fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
    	fn.adicionaFilmeHotList(1, 1);
    	fn.cadastraFilme(4, "Avatar 2", "2009", "Disney+");
    	fn.adicionaFilmeHotList(4, 1);
    	assertEquals("🔥 Avatar 2, 2009\nDisney+", fn.detalhaFilme(4));
    	assertEquals("Avatar, 2009\nDisney+", fn.detalhaFilme(1));
    }
    
    @Test
    void testAdicionaFilmeNaHotListSemFilmeExistente() {
    	String resultado = fn.adicionaFilmeHotList(1, 1);
    	assertEquals("FILME NÃO ADICIONADO ANTERIORMENTE", resultado);
    }
    
    @Test
    void testAdicionaFilmeNaHotListJaAdicionadoAntes() {
    	fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
    	fn.adicionaFilmeHotList(1, 1);
    	String resultado = fn.adicionaFilmeHotList(1, 1);
    	assertEquals("FILME JÁ ADICIONADO NA HOTLIST", resultado);
    }
    
    @Test
    void testAdicionaFilmeNaHotListPosicaoInvalidaAcima() {
    	fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
    	String resultado = fn.adicionaFilmeHotList(1, 101);
    	assertEquals("POSIÇÃO INVÁLIDA", resultado);
    }
    
    @Test
    void testAdicionaFilmeNaHotListPosicaoInvalidaAbaixo() {
    	fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
    	String resultado = fn.adicionaFilmeHotList(1, 0);
    	assertEquals("POSIÇÃO INVÁLIDA", resultado);
    }
    
    @Test
    void testExibeHotlist() {
    	fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
    	fn.adicionaFilmeHotList(1, 1);
    	String resultado = fn.exibeHotList();
    	assertEquals("1 - Avatar, 2009\n", resultado);
    }
    
    @Test
    void testExibeHotlistSemFilme() {
    	String resultado = fn.exibeHotList();
    	assertEquals("", resultado);
    }
    
    @Test
    void testDetalhaFilmeDaHotList() {
    	fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
    	fn.adicionaFilmeHotList(1, 1);
    	String resultado = fn.detalhaFilme(1);
    	assertEquals("🔥 Avatar, 2009\nDisney+", resultado);
    }
    
    @Test
    void testDetalhaFilmeDepoisDeSubstituirNaHotlist() {
    	fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
    	fn.adicionaFilmeHotList(1, 1);
    	String resultado = fn.detalhaFilme(1);
    	assertEquals("🔥 Avatar, 2009\nDisney+", resultado);
    	fn.cadastraFilme(2, "Avatar 2", "2019", "Disney+");
    	fn.adicionaFilmeHotList(2, 1);
    	String resultado2 = fn.detalhaFilme(1);
    	assertEquals("Avatar, 2009\nDisney+", resultado2);
    }
    
    @Test
    void testRemoveFilmeHotListPosicaoInvalidaAlem() {
    	fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
    	fn.adicionaFilmeHotList(1, 1);
    	String resultado = fn.removeFilmeHotList(101);
    	assertEquals("POSIÇÃO INVÁLIDA", resultado);
    }
    
    @Test
    void testRemoveFilmeHotListPosicaoInvalidaAquem() {
    	fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
    	fn.adicionaFilmeHotList(1, 1);
    	String resultado = fn.removeFilmeHotList(0);
    	assertEquals("POSIÇÃO INVÁLIDA", resultado);
    }
    
    @Test
    void testRemoveFilmeHotList() {
    	fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
    	fn.adicionaFilmeHotList(1, 1);
    	String resultado = fn.removeFilmeHotList(1);
    	assertEquals("FILME REMOVIDO DA HOTLIST", resultado);
    }
    
    @Test
    void testRemoveFilmeHotListNaoExistente() {
    	String resultado = fn.removeFilmeHotList(1);
    	assertEquals("FILME NÃO EXISTENTE NA HOTLIST", resultado);
    }
}
