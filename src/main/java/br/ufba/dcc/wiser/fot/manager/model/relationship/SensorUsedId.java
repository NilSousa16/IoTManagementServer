package br.ufba.dcc.wiser.fot.manager.model.relationship;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import br.ufba.dcc.wiser.fot.manager.model.Device;
import br.ufba.dcc.wiser.fot.manager.model.Sensor;

@Embeddable
public class SensorUsedId implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue
	private long id;

	@ManyToOne
	private Device device;

	@ManyToOne
	private Sensor sensor;

	public SensorUsedId() {

	}

	public SensorUsedId(Device device, Sensor sensor) {
		this.device = device;
		this.sensor = sensor;
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

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((device == null) ? 0 : device.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((sensor == null) ? 0 : sensor.hashCode());
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
		SensorUsedId other = (SensorUsedId) obj;
		if (device == null) {
			if (other.device != null)
				return false;
		} else if (!device.equals(other.device))
			return false;
		if (id != other.id)
			return false;
		if (sensor == null) {
			if (other.sensor != null)
				return false;
		} else if (!sensor.equals(other.sensor))
			return false;
		return true;
	}

}
