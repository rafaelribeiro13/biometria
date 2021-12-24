package autenticacao.dao;

import static autenticacao.dao.ConnectionFactory.closeConnection;
import static autenticacao.dao.ConnectionFactory.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import autenticacao.model.Produtora;

/**
 * Essa classe é responsável por manipular as operações básicas
 * do banco de dados.
 *
 */
public class ProdutoraDAO {
	
	private static final String SELECT = "SELECT * FROM PRODUTORAS WHERE PROD_NOME=?";
	
	
	/**
	 * Retorna uma produtora consultada pelo seu nome.
	 * 
	 * @param nomeProdutora
	 * @return
	 * @throws SQLException
	 */
	public static Produtora consultar(String nomeProdutora) throws SQLException {
		
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Produtora produtora = null;
		
		try {
			ps = conn.prepareStatement(SELECT);
			ps.setString(1, nomeProdutora);
			rs = ps.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
					String nomeProd = rs.getString("PROD_NOME").trim();
					String enderecoProd = rs.getString("PROD_ENDERECO").trim();
					String produtosProd = rs.getString("PROD_PRODUTOS_AGRICOLAS").trim();
					String producaoAnualProd = rs.getString("PROD_PRODUCAO_ANUAL").trim();
					String destinoProducaoProd = rs.getString("PROD_DESTINO_PRODUCAO").trim();
					int numeroEmpregados = rs.getInt("PROD_NUMERO_EMPREGADOS");
					int numeroMaquinas = rs.getInt("PROD_NUMERO_MAQUINAS");
					String nivelAutomacaoProd = rs.getString("PROD_NIVEL_AUTOMACAO").trim();
					
					produtora = new Produtora(nomeProd, enderecoProd);
					produtora.setProdutos(produtosProd);
					produtora.setProducaoAnual(producaoAnualProd);
					produtora.setDestinoProducao(destinoProducaoProd);
					produtora.setNumeroEmpregados(numeroEmpregados);
					produtora.setNumeroMaquinas(numeroMaquinas);
					produtora.setNivelAutomacao(nivelAutomacaoProd);
				}
			}
		} catch (SQLException e) {
			throw new SQLException("Não foi possível realizar a consulta: " + e.getMessage());
		} finally {
			closeConnection(conn, ps, rs);
		}
		
		return produtora;
	
	}
	
}
