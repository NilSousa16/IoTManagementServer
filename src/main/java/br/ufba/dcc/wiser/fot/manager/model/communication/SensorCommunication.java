package br.ufba.dcc.wiser.fot.manager.model.communication;

/**
* Model for capturing the information received from the gateway.
*
* @author Nilson Rodrigues Sousa
*/
public class SensorCommunication extends DeviceCommunication {

	/* Description of the type of monitoring done by a sensor */
	private String monitor;

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

}
