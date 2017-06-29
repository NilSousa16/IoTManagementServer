package br.ufba.dcc.wiser.fot.manager.model.relationship;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import br.ufba.dcc.wiser.fot.manager.model.Device;
import br.ufba.dcc.wiser.fot.manager.model.Gateway;

@Embeddable
public class DeviceUsedId implements Serializable {
	// Realizar composição de chaves para permitir repetições no devicedused
	private static final long serialVersionUID = 1L;

	@GeneratedValue
	private long id;

	@ManyToOne
	private Gateway gateway;

	@ManyToOne
	private Device device;

	public DeviceUsedId() {

	}

	public long getId() {
		return id;
	}

	public DeviceUsedId(Gateway gateway, Device device) {
		this.gateway = gateway;
		this.device = device;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public Device getDevice() {
		return device;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((device == null) ? 0 : device.hashCode());
		result = prime * result + ((gateway == null) ? 0 : gateway.hashCode());
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
		DeviceUsedId other = (DeviceUsedId) obj;
		if (device == null) {
			if (other.device != null)
				return false;
		} else if (!device.equals(other.device))
			return false;
		if (gateway == null) {
			if (other.gateway != null)
				return false;
		} else if (!gateway.equals(other.gateway))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
