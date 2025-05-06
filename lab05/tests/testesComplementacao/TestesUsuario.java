package testesComplementacao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividades.Atividade;
import atividades.Estagio;
import atividades.Monitoria;
import atividades.PesquisaExtensao;
import atividades.RepresentacaoEstudantil;
import complementacao.Usuario;

class TestesUsuario {
	
	private Usuario usuario;
	private Atividade atividade;

	@BeforeEach
    public void setup() {
        usuario = new Usuario("Ana", "123.456.789-00", "senha123", "20230004");
        atividade = new PesquisaExtensao("123.456.789-00-1", 12, 22, 24, "Pesquisa");
    }
    
    // TESTES DO CONSTRUTOR
    
    @Test
    public void testConstrutorValidoHappyDay() {
        Usuario u = new Usuario("Maria", "123.456.789-00", "senha123", "20230001");
        assertEquals("Maria", u.getNome());
        assertEquals("123.456.789-00", u.getCpf());
        assertEquals("20230001", u.getMatricula());
        assertEquals(0, u.getPontosDicas());
        assertEquals(0, u.getQtdAtividades());
    }
    
    @Test
    public void testConstrutorSenhaInvalida() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("João", "123.456.789-00", "curta", "20230002");
        });
        assertEquals("SENHA COM TAMANHO INVÁLIDO!", e.getMessage());
    }

    
    @Test
    public void testConstrutorCpfInvalido() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("Ana", "12345678900", "senha123", "20230003");
        });
        assertEquals("CPF COM TAMANHO INVÁLIDO!", e.getMessage());
    }

    
    @Test
    public void testConstrutorNomeVazio() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("", "123.456.789-00", "senha123", "20230004");
        });
        assertEquals("ENTRADA VAZIA!", e.getMessage());
    }

    @Test
    public void testConstrutorSenhaVazia() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("aninha", "123.456.789-00", "", "20230004");
        });
        assertEquals("ENTRADA VAZIA!", e.getMessage());
    }
    
    @Test
    public void testConstrutorCpfVazio() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("ss", "", "senha123", "20230004");
        });
        assertEquals("ENTRADA VAZIA!", e.getMessage());
    }
    
    @Test
    public void testConstrutorMatriculaVazia() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("ss", "123.456.789-00", "senha123", "");
        });
        assertEquals("ENTRADA VAZIA!", e.getMessage());
    }
    
    @Test
    public void testConstrutorNomeNulo() {
        Exception e = assertThrows(NullPointerException.class, () -> {
            new Usuario(null, "123.456.789-00", "senha123", "20230004");
        });
        assertEquals("ENTRADA NULA!", e.getMessage());
    }

    @Test
    public void testConstrutorSenhaNula() {
        Exception e = assertThrows(NullPointerException.class, () -> {
            new Usuario("aninha", "123.456.789-00", null, "20230004");
        });
        assertEquals("ENTRADA NULA!", e.getMessage());
    }

    @Test
    public void testConstrutorCpfNulo() {
        Exception e = assertThrows(NullPointerException.class, () -> {
            new Usuario("ss", null, "senha123", "20230004");
        });
        assertEquals("ENTRADA NULA!", e.getMessage());
    }

    @Test
    public void testConstrutorMatriculaNula() {
        Exception e = assertThrows(NullPointerException.class, () -> {
            new Usuario("ss", "123.456.789-00", "senha123", null);
        });
        assertEquals("ENTRADA NULA!", e.getMessage());
    }

    // 	TESTES DE ALTERAR SENHA
    
    @Test
    public void testAlteraSenhaIncorreta() {
        assertFalse(usuario.alteraSenha("errada123", "nova1234"));
    }

    @Test
    public void testAlteraSenhaNovaInvalida() {
        assertFalse(usuario.alteraSenha("senha123", "curta"));
    }

    @Test
    public void testAlteraSenhaCorreta() {
        assertTrue(usuario.alteraSenha("senha123", "nova5678"));
    }
    
    @Test
    public void testAlteraSenhaTentativaNula() {
        Exception e = assertThrows(NullPointerException.class, () -> {
            usuario.alteraSenha(null, "nova5678");
        });
        assertEquals("ENTRADA NULA!", e.getMessage());
    }

    @Test
    public void testAlteraSenhaNovaNula() {
        Exception e = assertThrows(NullPointerException.class, () -> {
            usuario.alteraSenha("senha123", null);
        });
        assertEquals("ENTRADA NULA!", e.getMessage());
    }

    @Test
    public void testAlteraSenhaTentativaVazia() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            usuario.alteraSenha("", "nova5678");
        });
        assertEquals("ENTRADA VAZIA!", e.getMessage());
    }

    @Test
    public void testAlteraSenhaNovaVazia() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            usuario.alteraSenha("senha123", "");
        });
        assertEquals("ENTRADA VAZIA!", e.getMessage());
    }
    
    // TESTES DE AUMENTAR PONTOS
    
    @Test
    public void testAumentaPontosNegativo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            usuario.aumentaPontosDicas(-5);
        });
        assertEquals("QUANTIDADE DE PONTOS INVÁLIDA!", e.getMessage());
    }

    @Test
    public void testAumentaPontosValido() {
        usuario.aumentaPontosDicas(10);
        assertEquals(10, usuario.getPontosDicas());
    }
    
    // TESTES DE EXIBIÇÃO DE ESTUDANTE
    
    @Test
    public void testToStringUsuario() {
        String esperada = "Nome: Ana | CPF: 123.456.789-00 | Matricula: 20230004 | Pontuação total: 0";
        assertEquals(esperada, usuario.toString());
    }
    
    @Test
    public void testExibeEstudanteRanking() {
        assertEquals("Nome: Ana | Pontuação total: 0", usuario.exibeEstudanteRanking());
    }

    @Test
    public void testExibeEstudante() {
        assertEquals("Nome: Ana | Matricula: 20230004 | Pontuação total: 0", usuario.exibeEstudante());
    }
    
    // TESTES DE VERIFICAÇÃO DE SENHA
    
    @Test
    public void testVerificaSenha() {
        assertTrue(usuario.verificaSenha("senha123"));
        assertFalse(usuario.verificaSenha("outra"));
    }
    
    // TESTES DE ATIVIDADES
    
    public void testAdicionaAtividade() {
        usuario.adicionaAtividade(atividade, "A1");
        assertEquals(5, usuario.getCreditosAtividade("PESQUISA_EXTENSAO"));
    }
    
    @Test
    public void testGetCreditosAtividadeComLimite() {
        Atividade a1 = new PesquisaExtensao("123.456.789-00-2", 12, 22, 18, "Pesquisa");
        Atividade a2 = new Estagio("123.456.789-00-3", 12, 22, 18, "Nubank");
        usuario.adicionaAtividade(a1, "E1");
        usuario.adicionaAtividade(a2, "E2");

        assertEquals(18, usuario.getCreditosAtividade("ESTAGIO")); 
    }
    
    @Test
    public void testAdicionaAtividadePesquisa() {
        PesquisaExtensao pesquisa = new PesquisaExtensao("P1", 3, 5, 18, "Iniciação Científica");
        usuario.adicionaAtividade(pesquisa, "P1");
        assertEquals(5, usuario.getCreditosAtividade("PESQUISA_EXTENSAO"));
    }

    @Test
    public void testCreditosComLimiteEstagio() {
        Estagio estagio1 = new Estagio("E1", 100, 15, 18, "Empresa A");
        Estagio estagio2 = new Estagio("E2", 80, 10, 18, "Empresa B");

        usuario.adicionaAtividade(estagio1, "E1");
        usuario.adicionaAtividade(estagio2, "E2");

        assertEquals(18, usuario.getCreditosAtividade("ESTAGIO")); 
    }
    
    // TESTES DE RELATORIOS
    
    @Test
    public void testGeraMapaCreditos() {
    	Atividade a1 = new Monitoria("M1", 1, 3, 16, "Cálculo I");
        usuario.adicionaAtividade(a1, "M1");
        String mapa = usuario.geraMapaCreditosAtividades();
        assertTrue(mapa.contains("MONITORIA - 3"));
    }

    @Test
    public void testMetaAlcancada() {
    	Atividade a1 = new RepresentacaoEstudantil("R1", 1, 22, 22, "DA");
        usuario.adicionaAtividade(a1, "R1");
        assertTrue(usuario.verificaMetaAlcancada());
    }
    
    @Test
    public void testMetaAlcancadaFalse() {
        assertFalse(usuario.verificaMetaAlcancada());
    }
    
    @Test
    public void testRelatorioAtividadePesquisaExtensao() {
        PesquisaExtensao p = new PesquisaExtensao("P2", 6, 7, 18, "PET");
        usuario.adicionaAtividade(p, "P2");
        String rel = usuario.getRelatorioPorAtividade("PESQUISA_EXTENSAO");
        assertEquals("PESQUISA_EXTENSAO: 7/18", rel);
    }
    
    @Test
    public void testRelatorioAtividadeMonitoria() {
        Monitoria m = new Monitoria("P2", 6, 7, 16, "PET");
        usuario.adicionaAtividade(m, "P2");
        String rel = usuario.getRelatorioPorAtividade("MONITORIA");
        assertEquals("MONITORIA: 7/16", rel);
    }
    
    @Test
    public void testRelatorioAtividadeEstagio() {
        Estagio m = new Estagio("P2", 6, 7, 18, "PET");
        usuario.adicionaAtividade(m, "P2");
        String rel = usuario.getRelatorioPorAtividade("ESTAGIO");
        assertEquals("ESTAGIO: 7/18", rel);
    }
    
    @Test
    public void testRelatorioAtividadeRepresentacaoEstudantil() {
    	RepresentacaoEstudantil m = new RepresentacaoEstudantil("P2", 6, 7, 2, "PET");
        usuario.adicionaAtividade(m, "P2");
        String rel = usuario.getRelatorioPorAtividade("REPRESENTACAO_ESTUDANTIL");
        assertEquals("REPRESENTACAO_ESTUDANTIL: 2/2", rel);
    }
    
    
    // TESTES ALTERANDO ATIVIDADES
    
    @Test
    public void testAlteraDescricao() {
        Monitoria m = new Monitoria("M2", 1, 2, 16, "POO");
        usuario.adicionaAtividade(m, "M2");
        assertTrue(usuario.alteraDescricaoAtividade("M2", "Nova descrição"));
    }
    
    @Test
    public void testAlteraDescricaoNaoTem() {
        assertFalse(usuario.alteraDescricaoAtividade("M2", "Nova descrição"));
    }

    @Test
    public void testAlteraComprovacao() {
        Estagio estagio = new Estagio("E3", 80, 5, 18, "Empresa Z");
        usuario.adicionaAtividade(estagio, "E3");
        assertTrue(usuario.alteraComprovacaoAtividade("E3", "linkblablabla"));
    }
    
    @Test
    public void testAlteraComprovacaoNaoTem() {
        assertFalse(usuario.alteraComprovacaoAtividade("E3", "linkblablabla"));
    }
    // TESTES HISTORICO	

    @Test
    public void testHistoricoRelatorio() {
        usuario.adicionaRelatorio("2025-01-01", "Relatório inicial");
        assertTrue(usuario.listaHistorico().contains("Relatório inicial"));
    }
    
    @Test
    public void testRemoveItemHistorico() {
    	usuario.adicionaRelatorio("2025-01-01", "Relatório inicial");
    	assertTrue(usuario.removeItemHistorico("2025-01-01"));
        assertFalse(usuario.listaHistorico().contains("Relatório inicial"));
        assertFalse(usuario.removeItemHistorico("2025-01-02"));
    	
    }
    
    // TESTES EQUALS E COMPARETO
    
    @Test
    public void testHashCode() {
        Usuario u2 = new Usuario("João", "123.456.789-00", "12345678", "123");
        assertEquals(usuario.hashCode(), u2.hashCode());
    }
    
    public void testEqualsMesmoObjeto() {
    	Usuario u2 = usuario;
        assertTrue(u2.equals(usuario)); 
    }

    @Test
    public void testEqualsNull() {
        assertFalse(usuario.equals(null)); 
    }

    @Test
    public void testEqualsOutraClasse() {
        String outro = "blablabla";
        assertFalse(usuario.equals(outro)); 
    }

    @Test
    public void testEqualsCpfDiferente() {
        Usuario u2 = new Usuario("Maria", "000.111.222-33", "12345678", "mat");
        assertFalse(usuario.equals(u2)); 
    }

    @Test
    public void testEqualsCpfIgual() {
    	Usuario u2 = new Usuario("Maria", "123.456.789-00", "12345678", "mat");
        assertTrue(usuario.equals(u2)); 
    }
    
    

    public void testCompareToAnterior() {
        Usuario u2 = new Usuario("Maria", "111.222.333-44", "senha321", "123");
        usuario = new Usuario("Ana", "000.000.000-00", "senha000", "111");
        assertTrue(usuario.compareTo(u2) < 0);
    }

    @Test
    public void testCompareToIgual() {
        Usuario u2 = new Usuario("maria", "111.222.333-44", "senha321", "123");
        usuario = new Usuario("MARIA", "000.000.000-00", "senha000", "111");
        assertTrue(usuario.compareTo(u2) == 0);
    }

    @Test
    public void testCompareToMaior() {
        Usuario u2 = new Usuario("Ana", "111.222.333-44", "senha321", "123");
        usuario = new Usuario("Zeca", "000.000.000-00", "senha000", "111");
        assertTrue(usuario.compareTo(u2) > 0);
    }
}
