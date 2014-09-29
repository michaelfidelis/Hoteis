package br.com.michael.hotels.model.bean;

/**
 * @author Michael Fidelis
 */

public class Cliente {

	private String tipo;

	/**
	 * Construtor
	 * 
	 * @param tipo
	 *            [Premiado/Regular]
	 */
	public Cliente(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return "Tipo de Cliente: " + tipo;
	}

}
