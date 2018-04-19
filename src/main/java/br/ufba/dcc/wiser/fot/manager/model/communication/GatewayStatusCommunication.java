package br.ufba.dcc.wiser.fot.manager.model.communication;

import java.util.Calendar;

/**
* Model for capturing the information received from the gateway.
*
* @author Nilson Rodrigues Sousa
*/
public class GatewayStatusCommunication {

	private double baterryLevel;
	private long totalMemory;
	private long usedMemory;
	private long freeMemory;
	private double usedProcessor;
	private double freeProcessor;

	private Calendar date;

	private String mac;

	public double getBaterryLevel() {
		return baterryLevel;
	}

	public void setBaterryLevel(double baterryLevel) {
		this.baterryLevel = baterryLevel;
	}

	public long getTotalMemory() {
		return totalMemory;
	}

	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}

	public long getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(long usedMemory) {
		this.usedMemory = usedMemory;
	}

	public long getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(long freeMemory) {
		this.freeMemory = freeMemory;
	}

	public double getUsedProcessor() {
		return usedProcessor;
	}

	public void setUsedProcessor(double usedProcessor) {
		this.usedProcessor = usedProcessor;
	}

	public double getFreeProcessor() {
		return freeProcessor;
	}

	public void setFreeProcessor(double freeProcessor) {
		this.freeProcessor = freeProcessor;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
