package br.com.michael.hotels.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.michael.hotels.model.bean.Hotel;
import br.com.michael.hotels.model.bean.Orcamento;
import br.com.michael.hotels.model.bean.Reserva;
import br.com.michael.hotels.model.dao.ReservaDAO;

public class Control {

	private ReservaDAO reservaDao;
	private List<Reserva> reservas;
	public void setArquivo(File arquivo) {
		reservaDao = new ReservaDAO(arquivo);
		reservas = reservaDao.getReservas();
	}

	/**
	 * Retorna uma lista contendo todos os Hoteis
	 * 
	 * @return List<Hotel> com os hotéis
	 */
	private List<Hotel> getHotelList() {
		List<Hotel> hotels = new ArrayList<>();
		hotels.add(new Hotel("Lakewood", 3, 110, 90, 80, 80));
		hotels.add(new Hotel("Bridgewood", 4, 160, 60, 110, 50));
		hotels.add(new Hotel("Ridgewood", 5, 220, 150, 100, 40));
		return hotels;
	}

	/**
	 * Retorna
	 * @return String com informaçoes ho hotel
	 */
	public String hoteisString() {
		String stringHoteis = "";
		for (int i = 0; i < getHotelList().size(); i++) {
			stringHoteis += getHotelList().get(i).getTabelaPreço();
		}
		return stringHoteis;
	}

	public void exibeReservas() {
		for (int i = 0; i < reservas.size(); i++) {
			System.out.println("[" + i + "] " + reservas.get(i));
		}
	}

	/**
	 * Gera uma String com as informações das reservas mais baratas, 
	 * @return <code>String</code>
	 */
	private List<Reserva> getReservasMaisBaratas() {
		List<Orcamento> orcamentos = new ArrayList<>();
		List<Hotel> hotelList = getHotelList();

		Orcamento orcamento;
		for (int x = 0; x < reservas.size(); x++) {
			for (int i = 0; i < hotelList.size(); i++) {
				orcamentos
						.add(new Orcamento(reservas.get(x), hotelList.get(i)));
			}
			orcamento = Orcamento.getMaisBatato(orcamentos);
			reservas.get(x).setHotel(orcamento.getHotel());
			reservas.get(x).setValorReserva(orcamento.getValorOrcamento());
			
		}

		return reservas;
	}
	
	public String reservasMaisBaratasToString(){
		List<Reserva> reservaList = getReservasMaisBaratas();
		String reservaString = "ATENÇÃO:\n O hotel informado na reserva é o mais barato com base nas \ninformações passadas\n\n";
		
		for(Reserva r : reservaList) {
		reservaString += r.toString();	
		}
		
		return reservaString;
	}

}

