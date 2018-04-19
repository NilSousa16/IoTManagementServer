package br.ufba.dcc.wiser.fot.manager.util;

/**
 * Class with utility methods
 * 
 * @author Nilson Rodrigues Sousa
 */
public class ConverterStringIp {
	
	public static String converterStringIp (String ip){
		
		String asx = "";

		for (int i = 1; i < ip.length(); i++) {

			if (ip.charAt(i) == ']') {
				break;
			}
			asx += ip.charAt(i);
		}

		return asx;
	}

}
