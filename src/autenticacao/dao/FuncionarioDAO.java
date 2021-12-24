package autenticacao.dao;

import static autenticacao.dao.ConnectionFactory.closeConnection;
import static autenticacao.dao.ConnectionFactory.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import autenticacao.model.Funcionario;

/**
 * Essa classe é reponsável por manipular as operações básicas
 * do banco de dados.
 *
 */
public class FuncionarioDAO {

	private final static String SELECT = "SELECT * FROM FUNCIONARIOS WHERE FUNC_DIGITAL=?";
	
	
	/**
	 * Retorna um funcionário referente a digital consultada.
	 * 
	 * @param caminhoDigital
	 * @return
	 * @throws SQLException
	 */
	public static Funcionario consultar(String caminhoDigital) throws SQLException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		Funcionario func = null;
		
		conn = getConnection();
		
		try {
			ps = conn.prepareStatement(SELECT);
			ps.setString(1, caminhoDigital);
			rs = ps.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
					String nome = rs.getString("FUNC_NOME").trim();
					String sobreNome = rs.getString("FUNC_SOBRENOME").trim();
					String cargo = rs.getString("FUNC_CARGO").trim();
					
					func = new Funcionario((nome + " " + sobreNome), cargo);
				}
			}
		} catch (SQLException e) {
			throw new SQLException("Não foi possível realizar a consulta: " + e.getMessage());
		} finally {
			closeConnection(conn, ps, rs);
		}
		
		return func;
		
	}
	
}
