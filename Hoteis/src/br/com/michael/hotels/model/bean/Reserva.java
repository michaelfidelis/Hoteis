package br.com.michael.hotels.model.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Reserva {
	private Hotel hotel;
	private Cliente cliente;
	private List<Calendar> datas;
	private double valorReserva;

	public Reserva(Cliente cliente, List<Calendar> datas) {
		this.cliente = cliente;
		this.datas = datas;
		this.hotel = null;
		this.valorReserva = 0;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<Calendar> getDatas() {
		return datas;
	}

	public void setValorReserva(double valorReserva) {
		this.valorReserva = valorReserva;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyyy", Locale.US);
		String conjuntoDatas = "";
		for (Calendar c : datas) {
			conjuntoDatas += sdf.format(c.getTime()) + " ";
		}

		return cliente.toString() 
				+ "\nDatas de Hospedagem: " + conjuntoDatas
				+  ((this.hotel == null) ?  "" : "\n"+hotel.toString())
				+ "\nValor da Reserva:"+ valorReserva
				+ "\n --\n";
	}

}
