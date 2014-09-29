package br.com.michael.hotels.model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import br.com.michael.hotels.model.bean.Cliente;
import br.com.michael.hotels.model.bean.Reserva;

public class ReservaDAO {

	// Caminho do arquivo
	private File file;

	// Registros contidos no arquivo
	private List<String> registros;

	/**
	 * Construtor recebe o caminho do arquivo
	 * 
	 * @param file
	 *            Caminho do arquivo contendo os dados
	 */
	public ReservaDAO(File file) {
		this.file = file;
		this.registros = getRegistros();

	}

	/**
	 * Monta a reserva com o tipo de cliente e as datas de hospedagem
	 * 
	 * @return
	 */
	public List<Reserva> getReservas() {
		List<Reserva> reservas = new ArrayList<>();

		for (int i = 0; i < getNumRegistros(); i++) {
			Cliente cliente = getCliente(i);
			List<Calendar> datas = getDatas(i);

			Reserva reserva = new Reserva(cliente, datas);

			reservas.add(reserva);
		}
		return reservas;
	}

	/**
	 * Retorna um <code>List</code> com os registros do arquivo
	 * 
	 * @return List<String>
	 */
	private List<String> getRegistros() {
		List<String> registros = new ArrayList<>();
		String linha;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)))) {

			linha = br.readLine();
			while (linha != null) {
				registros.add(linha);
				linha = br.readLine();
			}
		} catch (Exception e) {
			e.getMessage();
		}

		return registros;
	}

	/**
	 * Retorna o número de registros do arquivo
	 * 
	 * @return int com a quantidade de registros
	 */
	private int getNumRegistros() {
		return registros.size();
	}

	/**
	 * Separa o tipo do cliente das datas, caso não seja informado um tipo,
	 * retorna 'Regular'
	 * 
	 * @param index
	 *            Número da linha do registro
	 * @return O tipo de cliente (Rewards/Regular)
	 */
	private Cliente getCliente(int index) {
		Cliente cliente;
		String registro = registros.get(index);
		String tipo = (String) registro.subSequence(0, registro.indexOf(":"));
		cliente = new Cliente(tipo);
		return cliente;
	}

	/**
	 * Separa as datas do tipo de cliente. Neste metodo retiro o dia da semana
	 * do registro, pois a classe Calendar já fornece esta informação.
	 * 
	 * @param text
	 * @return um array de strings com todas as datas de hospedagem
	 */
	private List<Calendar> getDatas(int index) {
		List<Calendar> listDatas = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMMyyyy", Locale.US);

		String registro = registros.get(index);

		// Crio uma String contendo somente as datas
		String textoDatas = (String) registro.subSequence(
				registro.indexOf(":") + 1, registro.length());

		// Separo as datas e insiro em um List<Calendar>
		for (String s : textoDatas.split(",")) {
			Calendar calendar = new GregorianCalendar();
			Date date;

			try {
				date = simpleDateFormat.parse(formataData(s));

				// Calendar recebe uma nova data
				calendar.setTime(date);

			} catch (ParseException e) {
				e.printStackTrace();
			}

			// Adiciona o calendar na lista
			listDatas.add(calendar);
		}

		return listDatas;

	}

	/**
	 * 
	 * @param textData
	 *            Texto a ser alterado
	 * @return Texto alterdo
	 */
	public String formataData(String textData) {
		String data = textData.substring(0, textData.indexOf("("));
		return data.trim();
	}

}
