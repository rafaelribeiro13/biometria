package autenticacao.controller;

import java.sql.SQLException;

import autenticacao.model.DadosFiscais;

/**
 * Essa classe é responsável por solicitar a consulta dos dados
 * fiscais de uma produtora para a camada de domínio da aplicação.
 * 
 */
public class DadosFiscaisController {

	/**
	 * Retorna os dados fiscais de uma produtora se a mesma existe 
	 * na consulta.
	 * 
	 * @param codProdutora
	 * @return
	 */
	public static DadosFiscais consultarDadosFiscais(int codProdutora) {
		
		DadosFiscais infosFiscais = null;
		
		try {
			infosFiscais = DadosFiscais.consultar(codProdutora);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return infosFiscais;
		
	}
	
}
