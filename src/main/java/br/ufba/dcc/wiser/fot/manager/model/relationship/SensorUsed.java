package br.ufba.dcc.wiser.fot.manager.model.relationship;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.ufba.dcc.wiser.fot.manager.model.Sensor;

@Entity
@Table(name = "sensor_used")
public class SensorUsed implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Id
	@ManyToOne
	private DeviceUsed deviceUsed;

	@Id
	@ManyToOne
	private Sensor sensor;

	private boolean status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DeviceUsed getDeviceUsed() {
		return deviceUsed;
	}

	public void setDeviceUsed(DeviceUsed deviceUsed) {
		this.deviceUsed = deviceUsed;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceUsed == null) ? 0 : deviceUsed.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((sensor == null) ? 0 : sensor.hashCode());
		result = prime * result + (status ? 1231 : 1237);
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
		SensorUsed other = (SensorUsed) obj;
		if (deviceUsed == null) {
			if (other.deviceUsed != null)
				return false;
		} else if (!deviceUsed.equals(other.deviceUsed))
			return false;
		if (id != other.id)
			return false;
		if (sensor == null) {
			if (other.sensor != null)
				return false;
		} else if (!sensor.equals(other.sensor))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

}
