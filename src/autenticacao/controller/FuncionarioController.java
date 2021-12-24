package autenticacao.controller;

import java.sql.SQLException;

import autenticacao.model.Funcionario;

/**
 * Essa classe é responsável por solicitar a consulta de um funcionário
 * para a camada de domínio da aplicação através de uma digital.
 *
 */
public class FuncionarioController {

	/**
	 * Retorna um funcionário se o mesmo existe na consulta.
	 * 
	 * @param caminhoDigital
	 * @return
	 */
	public static Funcionario consultarFuncionario(String caminhoDigital) {
		
		Funcionario func = null;
		
		try {
			func = Funcionario.consultar(caminhoDigital);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return func;
		
	}
	
}
