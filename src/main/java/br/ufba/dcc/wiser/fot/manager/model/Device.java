package br.ufba.dcc.wiser.fot.manager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import br.ufba.dcc.wiser.fot.manager.model.relationship.ActuatorUsed;
import br.ufba.dcc.wiser.fot.manager.model.relationship.SensorUsed;

/**
 *
 * @author Nilson Rodrigues Sousa
 */
@Entity
@Table(name = "device")
public class Device implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String manufacturer;
	private String version;

	@ManyToOne
	private Location location;
	private String ip; // in tests
	@Id
	private String mac; // in tests
	private String descriptionSemantic;

	@ManyToOne
	private Gateway gateway;

	@Type(type = "true_false")
	private boolean status;

	@OneToMany(mappedBy = "id.device", fetch = FetchType.LAZY)
	private List<SensorUsed> listSensor = new ArrayList<SensorUsed>();

	@OneToMany(mappedBy = "id.device", fetch = FetchType.LAZY)
	private List<ActuatorUsed> listActuator = new ArrayList<ActuatorUsed>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getDescriptionSemantic() {
		return descriptionSemantic;
	}

	public void setDescriptionSemantic(String descriptionSemantic) {
		this.descriptionSemantic = descriptionSemantic;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<SensorUsed> getListSensor() {
		return listSensor;
	}

	public void setListSensor(List<SensorUsed> listSensor) {
		this.listSensor = listSensor;
	}

	public List<ActuatorUsed> getListActuator() {
		return listActuator;
	}

	public void setListActuator(List<ActuatorUsed> listActuator) {
		this.listActuator = listActuator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descriptionSemantic == null) ? 0 : descriptionSemantic.hashCode());
		result = prime * result + ((gateway == null) ? 0 : gateway.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((listActuator == null) ? 0 : listActuator.hashCode());
		result = prime * result + ((listSensor == null) ? 0 : listSensor.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Device other = (Device) obj;
		if (descriptionSemantic == null) {
			if (other.descriptionSemantic != null)
				return false;
		} else if (!descriptionSemantic.equals(other.descriptionSemantic))
			return false;
		if (gateway == null) {
			if (other.gateway != null)
				return false;
		} else if (!gateway.equals(other.gateway))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (listActuator == null) {
			if (other.listActuator != null)
				return false;
		} else if (!listActuator.equals(other.listActuator))
			return false;
		if (listSensor == null) {
			if (other.listSensor != null)
				return false;
		} else if (!listSensor.equals(other.listSensor))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status != other.status)
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}
