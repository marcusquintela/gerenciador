package br.com.alura.gerenciador.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilData {
	
	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public static Date converteData(String dataAbertura) {
		
		Date retorno = null;
		
		try {
			retorno = simpleDateFormat.parse(dataAbertura);
		} catch (ParseException e) {
			e.printStackTrace();			
		}
		
		return retorno;
	}

}
