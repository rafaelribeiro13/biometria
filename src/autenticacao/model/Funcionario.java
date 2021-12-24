package autenticacao.model;

import java.sql.SQLException;

import autenticacao.dao.FuncionarioDAO;

/**
 * Essa classe representa um funcionário da aplicação.
 *
 */
public class Funcionario {
	
	private String nome = "";
	private String cargo = "";
	
	
//	public Funcionario(String nome, Cargo cargo) {
//		this.setNome(nome);
//		this.setCargo(cargo);
//	}

	public Funcionario(String nome, String cargo) {
		this.setNome(nome);
		this.setCargo(cargo);
	}

	public static Funcionario consultar(String caminhoDigital) throws SQLException {
		return FuncionarioDAO.consultar(caminhoDigital);
	}
	
	private void setNome(String nome) {
		this.nome = nome;
	}
	
	private void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getCargo() {
		return this.cargo;
	}

	@Override
	public String toString() {
		return "Funcionario [nome=" + nome + ", cargo=" + cargo + "]";
	}
	
}
