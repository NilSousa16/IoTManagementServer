package br.ufba.dcc.wiser.fot.manager.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Bundler {

	// private String state;
	@Id
	private String name;
	private String version;
	private String location;

	@ManyToMany(mappedBy = "listBundler")
	private List<Gateway> gateway;

	// public String getState() {
	// return state;
	// }
	//
	// public void setState(String state) {
	// this.state = state;
	// }

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

	public List<Gateway> getGateway() {
		return gateway;
	}

	public void setGateway(List<Gateway> gateway) {
		this.gateway = gateway;
	}

}
