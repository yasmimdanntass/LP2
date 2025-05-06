package facade;

import controllers.AtividadeController;
import controllers.DicaController;
import controllers.GeradorRelatorio;
import controllers.UsuarioController;

public class FacadeComplementaACAO {
	
	private UsuarioController uc;
	private DicaController dc;
	private AtividadeController ac;
	private GeradorRelatorio gr;
	
	public FacadeComplementaACAO() {
		this.uc = new UsuarioController();
		this.dc = new DicaController();
		this.ac = new AtividadeController();
		this.gr = new GeradorRelatorio();
	}

	public boolean criarEstudante(String nome, String cpf, String senha, String matricula) {
		return uc.criaEstudante(nome, cpf, senha, matricula);
	}
	
	public String[] exibirEstudantes() {
		return uc.listaUsuariosPorNome();
	}
	
	public boolean alterarSenhaEstudante(String cpf, String senhaAntiga, String novaSenha) {
		return uc.alteraSenhaEstudante(cpf, senhaAntiga, novaSenha);
	}
	
	public int adicionarDica(String cpf, String senha, String tema) {
		return dc.adicionaDica(uc.getUsuario(cpf, senha), tema);
	}
	
	public boolean adicionarElementoTextoDica(String cpf, String senha, int posicao, String texto) {
		return dc.adicionaElementoTextoDica(uc.getUsuario(cpf, senha), posicao, texto);

	}
	
	public boolean adicionarElementoMultimidiaDica(String cpf, String senha, int posicao, String link, String cabecalho, int tempo) {
		return dc.adicionaElementoMultimidiaDica(uc.getUsuario(cpf, senha), posicao, link, cabecalho, tempo);
	}
	
	public boolean adicionarElementoReferenciaDica(String cpf, String senha, int posicao, String titulo, String fonte, int ano, boolean conferida, int importancia) {
		return dc.adicionaElementoReferenciaDica(uc.getUsuario(cpf, senha), posicao, titulo, fonte, ano, conferida, importancia);
	}
	
	public String[] listarDicas() {
		return dc.listaDicas();
	}
	
	public String [] listarDicasDetalhes() {
		return dc.listaDicasDetalhes();
	}
	
	public String listarDica(int posicao) {
		return dc.listaDica(posicao);
	}
	public String listarDicaDetalhes(int posicao) {
		return dc.listaDicaDetalhes(posicao);
	}
	public String[] listarUsuariosRankingDicas() {
		return uc.listaUsuariosRankingDicas();
	}

	public String criarAtividadeMonitoriaEmEstudante(String cpf, String senha, int unidadeAcumulada, String disciplina) {
		return ac.criaAtividadeMonitoriaEmUsuario(uc.getUsuario(cpf, senha), unidadeAcumulada, disciplina);
	}
	
	public boolean alterarDescricaoAtividade(String cpf, String senha, String codigoAtividade, String descricao) {
		return ac.alteraDescricaoAtividade(uc.getUsuario(cpf, senha), codigoAtividade, descricao);
	}
	
	public boolean alterarComprovacaoAtividade(String cpf, String senha, String codigoAtividade, String linkComprovacao) {
		return ac.alteraComprovacaoAtividade(uc.getUsuario(cpf, senha), codigoAtividade, linkComprovacao);
	}
	
	public String criarAtividadePesquisaExtensaoEmEstudante(String cpf, String senha, int unidadeAcumulada, String subtipo) {
		return ac.criaAtividadePesquisaExtensaoEmUsuario(uc.getUsuario(cpf, senha), unidadeAcumulada, subtipo);
	}
	
	public String criarAtividadeEstagioEmEstudante(String cpf, String senha, int unidadeAcumulada, String nomeEmpresa) {
		return ac.criaAtividadeEstagioEmUsuario(uc.getUsuario(cpf, senha), unidadeAcumulada, nomeEmpresa);
	}
	
	public String criarAtividadeRepresentacaoEstudantil(String cpf, String senha, int unidadeAcumulada, String subtipo) {
		return ac.criaAtividadeRepresentacaoEstudantilEmUsuario(uc.getUsuario(cpf, senha), unidadeAcumulada, subtipo);
	}
	
	public int creditosAtividade(String cpf, String senha, String tipo) {
		return ac.creditosAtividade(uc.getUsuario(cpf, senha), tipo);
	}
	
	public String gerarMapaCreditosAtividades(String cpf, String senha) {
		return ac.geraMapaCreditosAtividades(uc.getUsuario(cpf, senha));
	}
	
	public boolean verificarMetaAlcancada(String cpf, String senha) {
		return ac.verificaMetaAlcancada(uc.getUsuario(cpf, senha));
	}

	public String gerarRelatorioFinal(String cpf, String senha) {
		return gr.geraRelatorioFinal(uc.getUsuario(cpf, senha));
	}
	
	public String gerarRelatorioFinalPorAtividade(String cpf, String senha, String tipoAtividade) {
		return gr.geraRelatorioFinal(uc.getUsuario(cpf, senha));
	}
	
	public String gerarRelatorioParcial(String cpf, String senha, boolean salvar) {
		return gr.geraRelatorioParcial(uc.getUsuario(cpf, senha), salvar);
	}
	
	public String gerarRelatorioParcialPorAtividade(String cpf, String senha, boolean salvar, String tipoAtividade) {
		return gr.geraRelatorioParcialPorAtividade(uc.getUsuario(cpf, senha), tipoAtividade, salvar);		
	}
	
	public String listarHistorico(String cpf, String senha) {
		return uc.listaHistorico(cpf, senha);
	}
	
	public boolean excluirItemHistorico(String cpf, String senha, String data) {
		return uc.excluiItemHistorico(cpf, senha, data);
	}

}