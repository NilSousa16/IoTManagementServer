package br.ufba.dcc.wiser.fot.manager.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestInstanc {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSSS");
		Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
		String dataFormatada = sdf.format(hora);
		
		System.out.println(">>>>Data: " + dataFormatada);

	}

}
