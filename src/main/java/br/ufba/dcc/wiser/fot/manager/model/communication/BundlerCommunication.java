package br.ufba.dcc.wiser.fot.manager.model.communication;

public class BundlerCommunication {

	private String name;
	private String version;
	private String location;
	private String state;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
