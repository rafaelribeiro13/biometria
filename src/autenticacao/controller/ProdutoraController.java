package autenticacao.controller;

import java.sql.SQLException;

import autenticacao.model.Produtora;

/**
 * Essa classe é responsável por solicitar a consulta das informações
 * de uma produtora para a camada de domínio da aplicação.
 *
 */
public class ProdutoraController {

	/**
	 * Retorna uma produtora se a mesma existe na consulta.
	 * 
	 * @param nomeProdutora
	 * @return
	 */
	public static Produtora consultarProdutora(String nomeProdutora) {
		
		Produtora produtora = null;

		try {
			produtora = Produtora.consultar(nomeProdutora);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produtora;
		
	}
	
}
