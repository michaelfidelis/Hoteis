package br.com.michael.hotels.model.bean;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Orcamento implements Comparable<Orcamento> {

	private Reserva reserva;
	private Hotel hotel;
	private double valorOrcamento;

	public Orcamento(Reserva reserva, Hotel hotel) {
		this.reserva = reserva;
		this.hotel = hotel;
		this.valorOrcamento = getValorOrcamento();
	}

	/**
	 * Faz o orcamento dado um hotel e uma reserva.
	 * 
	 * @param reserva
	 *            A reserva contendo cliente e datas
	 * @param hotel
	 *            - O hotel contendo o nome e os valores de diárias
	 * @return double com o valor da reserva
	 */
	public double getValorOrcamento() {
		List<Calendar> datas = reserva.getDatas();
		Cliente cliente = reserva.getCliente();

		int diasUteis = datas.size();

		int fimDeSemana = 0;
		double valorTotal = 0;

		// Conta quantos dias há na reserva.
		for (Calendar c : datas) {
			if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
					|| c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				fimDeSemana++;
				diasUteis--;
			}
		}

		// Caso o cliente seja premiado,
		if (cliente.getTipo().equalsIgnoreCase("rewards")) {
			valorTotal += (fimDeSemana * hotel.getPrecoFimDeSemanaPremiado());
			valorTotal += (diasUteis * hotel.getPrecoSemanaPremiado());
		} else {
			valorTotal += (fimDeSemana * hotel.getPrecoFimDeSemana());
			valorTotal += (diasUteis * hotel.getPrecoSemana());
		}

		return valorTotal;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public String toString() {
		return hotel.toString() + "\n" + "Valor: " + valorOrcamento + "\n";
	}

	@Override
	public int compareTo(Orcamento o) {
		if (this.valorOrcamento < o.valorOrcamento) {
			return -1;
		}

		if (this.valorOrcamento > o.valorOrcamento) {
			return 1;
		}

		if (this.valorOrcamento == o.valorOrcamento
				&& this.hotel.getClassificacao() > o.hotel.getClassificacao()) {
			return -1;
		}

		return 0;
	}

	public static Orcamento getMaisBatato(List<Orcamento> orcamentos) {
		Collections.sort(orcamentos);
		return orcamentos.get(0);
	}
}
