package br.ufba.dcc.wiser.fot.manager.model.relationship;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.ufba.dcc.wiser.fot.manager.model.Actuador;

@Entity
@Table(name = "actuador_used")
public class ActuadorUsed implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Id
	@ManyToOne
	private DeviceUsed deviceUsed;

	@Id
	@ManyToOne
	private Actuador actuador;

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setDeviceUsed(DeviceUsed deviceUsed) {
		this.deviceUsed = deviceUsed;
	}

	public Actuador getActuador() {
		return actuador;
	}

	public void setActuador(Actuador actuador) {
		this.actuador = actuador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actuador == null) ? 0 : actuador.hashCode());
		result = prime * result + ((deviceUsed == null) ? 0 : deviceUsed.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		ActuadorUsed other = (ActuadorUsed) obj;
		if (actuador == null) {
			if (other.actuador != null)
				return false;
		} else if (!actuador.equals(other.actuador))
			return false;
		if (deviceUsed == null) {
			if (other.deviceUsed != null)
				return false;
		} else if (!deviceUsed.equals(other.deviceUsed))
			return false;
		if (id != other.id)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

}
