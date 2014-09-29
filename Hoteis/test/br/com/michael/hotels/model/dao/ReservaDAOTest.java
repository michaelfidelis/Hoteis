package br.com.michael.hotels.model.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class ReservaDAOTest {

	private ReservaDAO rd;
	private File arquivo;

	@Before
	public void setUp() throws Exception {
		arquivo = new File("DadosParaTestes.txt");
		rd = new ReservaDAO(arquivo);
	}

	@Test
	public void verificaSeEArquivoValido() {
		assertTrue(arquivo.isFile());
	}
	
/*	Montei esses testes, porem verifiquei que não havia necessidade de usa -los
 *  Não apaguei, pois posso precisar futuramente.
 *   
	@Test
	public void verificaSeHaRegistros(){
		assertFalse(rd.getRegistros().isEmpty());
		assertTrue(rd.getNumRegistros() > 0);
	}
	
	@Test
	public void verificaSeHaClientes(){
		assertTrue(rd.getCliente(0) != null);
	}
	*/
	
	@Test
	public void verificaAFormatacaoDasDatas(){
		assertEquals("16Mar2009", rd.formataData("16Mar2009(mon)"));
		assertEquals("17Mar2009", rd.formataData("17Mar2009(tues)"));
		assertEquals("18Mar2009", rd.formataData("18Mar2009(wed)"));
		assertEquals("26Mar2009", rd.formataData("26Mar2009(thur)"));
	}
	
	@Test
	public void retornaOsDiasDaSemana() throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMMyyyy", Locale.US);

		Calendar calendar = new GregorianCalendar();
		Date date = simpleDateFormat.parse("17Mar2009");
		calendar.setTime(date);
		assertEquals(3, calendar.get(Calendar.DAY_OF_WEEK));
		
	}

}
