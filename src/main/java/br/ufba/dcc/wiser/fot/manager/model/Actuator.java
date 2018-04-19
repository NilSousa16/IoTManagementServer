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
@Table(name = "actuator")
public class Actuator implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ActuatorId id;

	private String manufacturer;
	private String action;
	private String descriptionSemantic;

	public ActuatorId getId() {
		return id;
	}

	public void setId(ActuatorId id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((descriptionSemantic == null) ? 0 : descriptionSemantic.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
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
		Actuator other = (Actuator) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
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
		return true;
	}

}
