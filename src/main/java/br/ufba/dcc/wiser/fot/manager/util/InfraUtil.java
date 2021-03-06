package br.ufba.dcc.wiser.fot.manager.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

/**
 * Class with utility methods
 * 
 * @author Nilson Rodrigues Sousa
 */
public class InfraUtil {
	
	/**
	 * Method that returns the ip address of the local machine
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return String - ip address
	 */
	public static String getIpMachine() {
		String ipAddress = null;
        Enumeration<NetworkInterface> net = null;
        try {
            net = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        while (net.hasMoreElements()) {
            NetworkInterface element = net.nextElement();
            Enumeration<InetAddress> addresses = element.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress ip = addresses.nextElement();

                if (ip.isSiteLocalAddress()) {
                    ipAddress = ip.getHostAddress();
                }           
            }
        }
        return ipAddress;
	}
	
	/**
	 * Method that returns date and time in the format dd/MM/yyyy h:mm - PM
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @return String - date and time
	 */
	public static String getDateHour() {
		String data = "dd/MM/yyyy";
		String hora = "h:mm - a";
		String data1, hora1;
		java.util.Date agora = new java.util.Date();
		SimpleDateFormat formata = new SimpleDateFormat(data);
		data1 = formata.format(agora);
		formata = new SimpleDateFormat(hora);
		hora1 = formata.format(agora);
		return data1 + " " + hora1;		
	}	
	

}
