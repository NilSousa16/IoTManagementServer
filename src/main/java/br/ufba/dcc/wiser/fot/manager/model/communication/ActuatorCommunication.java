package br.ufba.dcc.wiser.fot.manager.model.communication;

/**
* Model for capturing the information received from the gateway.
*
* @author Nilson Rodrigues Sousa
*/
public class ActuatorCommunication extends DeviceCommunication{
	
	/* Describe the action performed by an actuator */
	private String action;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
