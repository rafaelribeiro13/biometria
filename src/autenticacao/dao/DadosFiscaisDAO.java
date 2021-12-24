package autenticacao.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import autenticacao.model.DadosFiscais;

import static autenticacao.dao.ConnectionFactory.getConnection;
import static autenticacao.dao.ConnectionFactory.closeConnection;

/**
 * Essa classe é responsável por manipular as operações básicas 
 * do banco de dados.
 *
 */
public class DadosFiscaisDAO {

	public static final String SELECT = "SELECT * FROM DADOS_FISCAIS WHERE PROD_CODIGO=?";
	
	
	/**
	 * Retorna os dados fiscais referente a produtora consultada.
	 * 
	 * @param codProdutora
	 * @return
	 * @throws SQLException
	 */
	public static DadosFiscais consultar(int codProdutora) throws SQLException {
		
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		DadosFiscais infosFiscais = null;
		
		try {
			ps = conn.prepareStatement(SELECT);
			ps.setInt(1, codProdutora);
			rs = ps.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
					BigDecimal incentivosFiscais = rs.getBigDecimal("DFISC_INCENTIVOS_FISCAIS");
					BigDecimal impostosMunicipais = rs.getBigDecimal("DFISC_IMPOSTOS_MUNICIPAIS");
					BigDecimal impostosEstaduais = rs.getBigDecimal("DFISC_IMPOSTOS_ESTADUAIS");
					BigDecimal impostosFederais = rs.getBigDecimal("DFISC_IMPOSTOS_FEDERAIS");
					BigDecimal taxasFederais = rs.getBigDecimal("DFISC_TAXAS_FEDERAIS");
					
					infosFiscais = new DadosFiscais();
					infosFiscais.setIncentivosFiscais(incentivosFiscais);
					infosFiscais.setImpostosMunicipais(impostosMunicipais);
					infosFiscais.setImpostosEstaduais(impostosEstaduais);
					infosFiscais.setImpostosFederais(impostosFederais);
					infosFiscais.setTaxasFederais(taxasFederais);
				}
			}
		} catch (SQLException e) {
			throw new SQLException("Não foi possível realizar a consulta: " + e.getMessage());
		} finally {
			closeConnection(conn, ps, rs);
		}
		
		return infosFiscais;
		
	}
	
}
