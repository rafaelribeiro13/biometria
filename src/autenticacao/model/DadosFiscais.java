package autenticacao.model;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

import autenticacao.dao.DadosFiscaisDAO;

/**
 * Essa classe representa os dados fiscais de uma produtora agrícola.
 *
 */
public class DadosFiscais {
	
	private BigDecimal incentivosFiscais = BigDecimal.ZERO;
	private BigDecimal impostosMunicipais = BigDecimal.ZERO;
	private BigDecimal impostosEstaduais = BigDecimal.ZERO;
	private BigDecimal impostosFederais = BigDecimal.ZERO;
	private BigDecimal taxasFederais = BigDecimal.ZERO;

	
	public static DadosFiscais consultar(int codProdutora) throws SQLException {
		return DadosFiscaisDAO.consultar(codProdutora);
	}
	
	
	public BigDecimal getIncentivosFiscais() {
		return incentivosFiscais;
	}


	public void setIncentivosFiscais(BigDecimal incentivosFiscais) {
		this.incentivosFiscais = incentivosFiscais;
	}


	public BigDecimal getImpostosMunicipais() {
		return impostosMunicipais;
	}


	public void setImpostosMunicipais(BigDecimal impostosMunicipais) {
		this.impostosMunicipais = impostosMunicipais;
	}


	public BigDecimal getImpostosEstaduais() {
		return impostosEstaduais;
	}


	public void setImpostosEstaduais(BigDecimal impostosEstaduais) {
		this.impostosEstaduais = impostosEstaduais;
	}


	public BigDecimal getImpostosFederais() {
		return impostosFederais;
	}


	public void setImpostosFederais(BigDecimal impostosFederais) {
		this.impostosFederais = impostosFederais;
	}


	public BigDecimal getTaxasFederais() {
		return taxasFederais;
	}


	public void setTaxasFederais(BigDecimal taxasFederais) {
		this.taxasFederais = taxasFederais;
	}
	
	@Override
	public String toString() {
		
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		
		return "Incentivos fiscais recebidos: " + nf.format(this.incentivosFiscais)
				+ "\nImpostos municipais pagos: " + nf.format(this.impostosMunicipais)
				+ "\nImpostos estaduais recolhidos: " + nf.format(this.impostosEstaduais)
				+ "\nImpostos federais pagos: " + nf.format(this.impostosFederais)
				+ "\nTaxas federasis pagas: " + nf.format(this.taxasFederais);
		
	}
	
}
