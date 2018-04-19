package br.ufba.dcc.wiser.fot.manager.model.relationship;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import br.ufba.dcc.wiser.fot.manager.model.Actuator;
import br.ufba.dcc.wiser.fot.manager.model.Device;

@Embeddable
public class ActuatorUsedId implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue
	private long id;

	@ManyToOne
	private Device device;

	@ManyToOne
	private Actuator actuator;

	public ActuatorUsedId() {

	}

	public ActuatorUsedId(Device device, Actuator actuator) {
		this.device = device;
		this.actuator = actuator;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Actuator getActuator() {
		return actuator;
	}

	public void setActuador(Actuator actuator) {
		this.actuator = actuator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actuator == null) ? 0 : actuator.hashCode());
		result = prime * result + ((device == null) ? 0 : device.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		ActuatorUsedId other = (ActuatorUsedId) obj;
		if (actuator == null) {
			if (other.actuator != null)
				return false;
		} else if (!actuator.equals(other.actuator))
			return false;
		if (device == null) {
			if (other.device != null)
				return false;
		} else if (!device.equals(other.device))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
