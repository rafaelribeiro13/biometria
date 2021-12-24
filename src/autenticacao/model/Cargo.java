package autenticacao.model;

/**
 * Essa classe representa os cargos dos funcionários da aplicação.
 *
 */
public enum Cargo {

	MINISTRO_AMBIENTE("Ministro do Meio Ambiente"),
	DIRETOR("Diretor");
	
	private String cargo = "";
	
	private Cargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getCargo() {
		return this.cargo;
	}
	
}
