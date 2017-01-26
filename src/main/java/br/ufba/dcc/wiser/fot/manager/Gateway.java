package br.ufba.dcc.wiser.fot.manager;

public class Gateway {

	private int usedMemory;
	private int freeMemory;
	private int totalMemory;
	private int usedProcessor;
	private int freeProcessor;
	private int totalProcessor;

	public int getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(int usedMemory) {
		this.usedMemory = usedMemory;
	}

	public int getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(int freeMemory) {
		this.freeMemory = freeMemory;
	}

	public int getTotalMemory() {
		return totalMemory;
	}

	public void setTotalMemory(int totalMemory) {
		this.totalMemory = totalMemory;
	}

	public int getUsedProcessor() {
		return usedProcessor;
	}

	public void setUsedProcessor(int usedProcessor) {
		this.usedProcessor = usedProcessor;
	}

	public int getFreeProcessor() {
		return freeProcessor;
	}

	public void setFreeProcessor(int freeProcessor) {
		this.freeProcessor = freeProcessor;
	}

	public int getTotalProcessor() {
		return totalProcessor;
	}

	public void setTotalProcessor(int totalProcessor) {
		this.totalProcessor = totalProcessor;
	}

}
