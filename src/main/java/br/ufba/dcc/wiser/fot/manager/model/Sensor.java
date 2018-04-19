package br.ufba.dcc.wiser.fot.manager.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Nilson Rodrigues Sousa
 */
@Entity
@Table(name = "sensor")
public class Sensor implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SensorId id;

	private String manufacturer;
	private String monitor;
	private String descriptionSemantic;

	public SensorId getId() {
		return id;
	}

	public void setId(SensorId id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public String getDescriptionSemantic() {
		return descriptionSemantic;
	}

	public void setDescriptionSemantic(String descriptionSemantic) {
		this.descriptionSemantic = descriptionSemantic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descriptionSemantic == null) ? 0 : descriptionSemantic.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((monitor == null) ? 0 : monitor.hashCode());
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
		Sensor other = (Sensor) obj;
		if (descriptionSemantic == null) {
			if (other.descriptionSemantic != null)
				return false;
		} else if (!descriptionSemantic.equals(other.descriptionSemantic))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (monitor == null) {
			if (other.monitor != null)
				return false;
		} else if (!monitor.equals(other.monitor))
			return false;
		return true;
	}

}
