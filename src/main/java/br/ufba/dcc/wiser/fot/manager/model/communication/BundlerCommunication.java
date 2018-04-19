package br.ufba.dcc.wiser.fot.manager.model.communication;

/**
* Model for capturing the information received from the gateway.
*
* @author Nilson Rodrigues Sousa
*/
public class BundlerCommunication {

	private String name;
	private String version;
	private String location;
	private String status;
	// private List<Service> listServiceProvide;
	// private List<Service> listServiceUse;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
