package br.com.michael.hotels.model.bean;

public class Hotel {

	private String nome;
	private double classificacao;

	private double precoSemana;
	private double precoFimDeSemana;

	private double precoSemanaPremiado;
	private double precoFimDeSemanaPremiado;

	public Hotel(String nome, double classificacao, double precoSemana,
			double precoFimDeSemana, double precoSemanaPremiado,
			double precoFimDeSemanaPremiado) {

		this.nome = nome;
		this.classificacao = classificacao;
		this.precoSemana = precoSemana;
		this.precoFimDeSemana = precoFimDeSemana;
		this.precoSemanaPremiado = precoSemanaPremiado;
		this.precoFimDeSemanaPremiado = precoFimDeSemanaPremiado;
	}

	@Override
	public String toString() {
		return "Hotel: " + nome;
	}
	
	/**
	 * 
	 * @return String Com informações de preço, classificação e nome do hotel
	 */
	public String getTabelaPreço(){
		return this.toString()
				+ "\nClassificação: "+ getClassificacao()
				+ "\nDias Úteis(Semana): " 
				+ "\n-- Cliente regular: $" + getPrecoSemana() 
				+ "\n-- Cliente premiado: $" + getPrecoSemanaPremiado()
				+ "\nFim de Semana: " 
				+ "\n-- Cliente regular: $" + getPrecoFimDeSemana()
				+ "\n-- Cliente premiado: $"+ getPrecoSemanaPremiado()
				+ "\n\n";
				
	}

	public double getPrecoSemana() {
		return precoSemana;
	}

	public double getPrecoFimDeSemana() {
		return precoFimDeSemana;
	}

	public double getPrecoSemanaPremiado() {
		return precoSemanaPremiado;
	}

	public double getPrecoFimDeSemanaPremiado() {
		return precoFimDeSemanaPremiado;
	}

	public double getClassificacao() {
		return classificacao;
	}
}
