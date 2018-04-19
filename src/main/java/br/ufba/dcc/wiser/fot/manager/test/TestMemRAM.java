package br.ufba.dcc.wiser.fot.manager.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TestMemRAM {

	public static void main(String[] args) throws IOException {
		// com.sun.management.OperatingSystemMXBean mxbean =
		// (com.sun.management.OperatingSystemMXBean) ManagementFactory
		// .getOperatingSystemMXBean();
		// System.out.println("Memoria Total : " +
		// mxbean.getTotalPhysicalMemorySize() + " Bytes ");
		// System.out.println("Memoria disponivel : " +
		// mxbean.getFreePhysicalMemorySize() + " Bytes");
		// System.out.println("#######################################");
		//
		// while (true) {
		// System.out.println("SystemLoadAverage: " +
		// mxbean.getSystemLoadAverage());
		// System.out.println("ProcessCpuLoad: " + mxbean.getProcessCpuLoad());
		// System.out.println("SystemCpuLoad: " + mxbean.getSystemCpuLoad());
		// System.out.println("ProcessCpuTime: " + mxbean.getProcessCpuTime());
		// try {
		// new Thread().sleep(2000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }

//		Runtime r = Runtime.getRuntime();
//		Process p = r.exec("ifconfig");
//		Scanner scanner = new Scanner(p.getInputStream());
//		String resultado = scanner.next();
//
//		System.out.print(resultado);

		System.out.println("#######################################");
		System.out.println("Recupera Tempo de Ociosidade CPU");

		Runtime run = Runtime.getRuntime();
		Process proc = null;
		Map<String, String> result = new HashMap<String, String>();
		//while(true) {
		try {
			//String command = "mpstat 1 1\n";

			String command = "free\n";
			
			proc = run.exec(command);

			result.put("input", inputStreamToString(proc.getInputStream()));

			// valor do endereço ip do gateway
			System.out.println(result);
//			System.out.println("Used CPU em %: " + result.toString().substring(192, 200).trim());
		} catch (IOException e) {
			e.printStackTrace();
		}//}
	}

	private static String inputStreamToString(InputStream isr) {
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(isr));
			StringBuilder sb = new StringBuilder();
			String s = null;

			while ((s = br.readLine()) != null) {
				sb.append(s + "\n");
			}

			return sb.toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
