package br.ufba.dcc.wiser.fot.manager.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalled;
import br.ufba.dcc.wiser.fot.manager.model.relationship.GatewayStatus;

/**
 *
 * @author Nilson Rodrigues Sousa
 */
@Entity
@Table(name = "gateway")
public class Gateway implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String mac;

	private String description;
	private String model;
	private String manufacturer;
	private String firmware;

	@Type(type = "true_false")
	private boolean status;
	private long storage;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastUpdate;
	private String ip;
	private String hostName;

	@ManyToOne
	private Location location;

	@OneToMany(mappedBy = "gateway", fetch = FetchType.LAZY)
	private List<GatewayStatus> gatewayStatus;

	@OneToMany(mappedBy = "id.gateway", fetch = FetchType.LAZY)
	private List<BundlerInstalled> listBundlerInstalled;

	@OneToMany(mappedBy = "gateway", fetch = FetchType.LAZY)
	private List<Device> listDevice;

	// private String[] intefaceNetwork;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getFirmware() {
		return firmware;
	}

	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getStorage() {
		return storage;
	}

	public void setStorage(long storage) {
		this.storage = storage;
	}

	public Calendar getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Calendar lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<GatewayStatus> getGatewayStatus() {
		return gatewayStatus;
	}

	public void setGatewayStatus(List<GatewayStatus> gatewayStatus) {
		this.gatewayStatus = gatewayStatus;
	}

	public List<BundlerInstalled> getListBundlerInstalled() {
		return listBundlerInstalled;
	}

	public void setListBundlerInstalled(List<BundlerInstalled> listBundlerInstalled) {
		this.listBundlerInstalled = listBundlerInstalled;
	}

	public List<Device> getListDevice() {
		return listDevice;
	}

	public void setListDevice(List<Device> listDevice) {
		this.listDevice = listDevice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((firmware == null) ? 0 : firmware.hashCode());
		result = prime * result + ((gatewayStatus == null) ? 0 : gatewayStatus.hashCode());
		result = prime * result + ((hostName == null) ? 0 : hostName.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + ((listBundlerInstalled == null) ? 0 : listBundlerInstalled.hashCode());
		result = prime * result + ((listDevice == null) ? 0 : listDevice.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + (int) (storage ^ (storage >>> 32));
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
		Gateway other = (Gateway) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (firmware == null) {
			if (other.firmware != null)
				return false;
		} else if (!firmware.equals(other.firmware))
			return false;
		if (gatewayStatus == null) {
			if (other.gatewayStatus != null)
				return false;
		} else if (!gatewayStatus.equals(other.gatewayStatus))
			return false;
		if (hostName == null) {
			if (other.hostName != null)
				return false;
		} else if (!hostName.equals(other.hostName))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		if (listBundlerInstalled == null) {
			if (other.listBundlerInstalled != null)
				return false;
		} else if (!listBundlerInstalled.equals(other.listBundlerInstalled))
			return false;
		if (listDevice == null) {
			if (other.listDevice != null)
				return false;
		} else if (!listDevice.equals(other.listDevice))
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
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (status != other.status)
			return false;
		if (storage != other.storage)
			return false;
		return true;
	}

}
