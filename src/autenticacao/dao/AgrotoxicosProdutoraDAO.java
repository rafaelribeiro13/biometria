package autenticacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static autenticacao.dao.ConnectionFactory.getConnection;
import static autenticacao.dao.ConnectionFactory.closeConnection;

/**
 * Essa classe é responsável por manipular as operações básicas
 * do banco de dados.
 *
 */
public class AgrotoxicosProdutoraDAO {

	private static final String SELECT = "SELECT C.AGRO_NOME "
			+ "FROM PRODUTORAS A INNER JOIN PRODUTORAS_AGROTOXICOS B "
			+ "ON A.PROD_CODIGO=B.PROD_CODIGO "
			+ "INNER JOIN AGROTOXICOS C "
			+ "ON B.AGRO_CODIGO=C.AGRO_CODIGO "
			+ "WHERE A.PROD_NOME=?";

	
	/**
	 * Retorna uma lista de agrotóxicos referente a produtora consultada.
	 * 
	 * @param nomeProdutora
	 * @return
	 * @throws SQLException
	 */
	public static List<String> consultar(String nomeProdutora) throws SQLException {
		
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> agrotoxicos = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(SELECT);
			ps.setString(1, nomeProdutora);
			rs = ps.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
					agrotoxicos.add(rs.getString("AGRO_NOME"));
				}
			}
		} catch (SQLException e) {
			throw new SQLException("Não foi possível realizar a consulta: " + e.getMessage());
		} finally {
			closeConnection(conn, ps, rs);
		}
		
		return agrotoxicos;
		
	}
	
}
