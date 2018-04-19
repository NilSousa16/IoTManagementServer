package br.ufba.dcc.wiser.fot.manager.test;

import java.io.IOException;

public class TxTGeneration {

	public static void main(String args[]) throws IOException {
//		OperatingSystemMXBean mxbean = (OperatingSystemMXBean) ManagementFactory
//				.getOperatingSystemMXBean();
//
//		/* file name generation */
//		Date data = new Date();
//		String filePath = "/home/nilson/Documents/LOG-" + data.toInstant().toString() + ".txt";
//		//String filePath = "/home/gateway/experimentos-" + data.toInstant().toString() + ".txt";
//		try {
//			FileWriter arq = new FileWriter(filePath);
//			PrintWriter gravarArq = new PrintWriter(arq);
//
//			/* total memory - free memory = used memory */
//			System.out.println("Runtime - Total memory:" + Runtime.getRuntime().totalMemory());
//			System.out.println("Runtime - Free memory:" + Runtime.getRuntime().freeMemory());
//			System.out.println("Runtime - Used memory:" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
//
//			System.out.println("\nMxbean - Total memory:" + mxbean.getTotalPhysicalMemorySize());
//			System.out.println("Mxbean - Free memory:" + mxbean.getFreePhysicalMemorySize());
//			System.out.println("Mxbean - Total memory:" + (mxbean.getTotalPhysicalMemorySize() - mxbean.getFreePhysicalMemorySize()));
////			gravarArq.printf("Used memory: %s Bytes %n",
////					mxbean.getTotalPhysicalMemorySize() - mxbean.getFreePhysicalMemorySize());
//			
//			/* Average system load at the last minute */
////			RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
////	        OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
//	        
//	        System.out.println("SystemCpuLoad:" + mxbean.getSystemCpuLoad());
////	        System.out.println("OS.SystemLoadAverage: " + os.getSystemLoadAverage());
//			System.out.println("SystemLoadAverage: " + mxbean.getSystemLoadAverage());
//			gravarArq.printf("SystemLoadAverage: %s", mxbean.getSystemLoadAverage());
//			
//			arq.close();
//		} catch (IOException e) {
//			System.out.println("Unable to save file.");
//			e.printStackTrace();
//		}
//		System.out.printf("Sucesso...");
	}

}
