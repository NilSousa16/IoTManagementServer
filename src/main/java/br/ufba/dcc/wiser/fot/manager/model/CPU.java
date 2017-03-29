package br.ufba.dcc.wiser.fot.manager.model;

public class CPU {
	private String vendor;
	private int clock;
	private int totalCores;
	private String model;

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public int getClock() {
		return clock;
	}

	public void setClock(int clock) {
		this.clock = clock;
	}

	public int getTotalCores() {
		return totalCores;
	}

	public void setTotalCores(int totalCores) {
		this.totalCores = totalCores;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
