package autenticacao.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import autenticacao.dao.AgrotoxicosProdutoraDAO;
import autenticacao.dao.ProdutoraDAO;

/**
 * Essa classe representa uma produtora agrícola da aplicação.
 *
 */
public class Produtora {

	private String nome = "";
	private String endereco = "";
	private String produtos = "";
	private String producaoAnual = "";
	private String destinoProducao = "";
	private int numeroEmpregados;
	private int numeroMaquinas;
	private String nivelAutomacao = "";
	private List<String> agrotoxicos = new ArrayList<>();

	public Produtora(String nome, String endereco) throws SQLException {
		this.setNome(nome);
		this.setEndereco(endereco);
		this.agrotoxicos = AgrotoxicosProdutoraDAO.consultar(nome);
	}

	public static Produtora consultar(String nomeProdutora) throws SQLException {
		return ProdutoraDAO.consultar(nomeProdutora);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setProdutos(String produtos) {
		this.produtos = produtos;
	}

	public void setProducaoAnual(String producaoAnual) {
		this.producaoAnual = producaoAnual;
	}

	public void setDestinoProducao(String destinoProducao) {
		this.destinoProducao = destinoProducao;
	}

	public void setNumeroEmpregados(int numeroEmpregados) {
		this.numeroEmpregados = numeroEmpregados;
	}

	public void setNumeroMaquinas(int numeroMaquinas) {
		this.numeroMaquinas = numeroMaquinas;
	}

	public void setNivelAutomacao(String nivelAutomacao) {
		this.nivelAutomacao = nivelAutomacao;
	}
	
	public List<String> getAgrotoxicos() {
		return Collections.unmodifiableList(this.agrotoxicos);
	}
	
	@Override
	public String toString() {
		
		return "Unidade Produtora: " + this.nome
				+ "\nEndereço do Produtor: " + this.endereco
				+ "\nProduto(s) agrícolas produzidos: " + this.produtos
				+ "\nProdução anual em quilogramas: " + this.producaoAnual
				+ "\nDestino da Produção: " + this.destinoProducao
				+ "\nNúmero de Empregados: " + this.numeroEmpregados
				+ "\nNúmero de Máquinas: " + this.numeroMaquinas
				+ "\nNível de automação: " + this.nivelAutomacao;
	
	}
	
}
