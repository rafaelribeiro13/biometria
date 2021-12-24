package autenticacao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Essa classe é responsável por abrir a conexão com o banco de dados
 *
 */
public class ConnectionFactory {

	private static final String URL = "jdbc:mysql://localhost:3306/<nomeBanco>?useSSL=false";
	private static final String USUARIO = "root";
	private static final String SENHA = "";
	
	/**
	 * Retorna a conexão com o banco de dados.
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	/**
	 * Fecha a conexão com o banco de dados.
	 * 
	 * @param con
	 */
	public static void closeConnection(Connection conn) {
		
		try {
			if (conn != null) {
				conn.close();
				System.out.println("Conexão com a base de dados fechada com sucesso.");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível fechar a conexão com a base de dados " + e.getMessage());
		}
		
	}
	
	/**
	 * Fecha todo recurso de manipulação do banco de dados.
	 * 
	 * @param conn
	 * @param ps
	 */
	public static void closeConnection(Connection conn, PreparedStatement ps) {
		
		try {
			if (conn != null) closeConnection(conn);
			
			if (ps != null) {
				ps.close();
				System.out.println("Statemnet fechado com sucesso.");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível fechar o Statement " + e.getMessage());
		}
		
	}
	
	/**
	 * Fecha todo recurso de manipulação do banco de dados.
	 * 
	 * @param conn
	 * @param ps
	 */
	public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
		
		try {
			if (conn != null && ps != null) closeConnection(conn, ps);
			
			if (rs != null) {
				rs.close();
				System.out.println("ResultSet fechado com sucesso.");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível fechar o ResultSet " + e.getMessage());
		}
		
	}
	
}
