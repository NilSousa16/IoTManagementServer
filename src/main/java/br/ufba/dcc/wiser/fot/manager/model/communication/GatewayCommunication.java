package br.ufba.dcc.wiser.fot.manager.model.communication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Model for capturing the information received from the gateway.
 *
 * @author Nilson Rodrigues Sousa
 */
public class GatewayCommunication {

	private String description; // description of OS in use
	private String model; // to be defined
	private String manufacturer; // to be defined
	private String firmware; // to be defined
	private boolean status; // on-off used server side opportunistically
	private long storage; // returns the list of components in existing stores
	private Calendar lastUpdate; // Method for the server side, but the client
									// can
									// register its actualization if possible

	// processor capacity
	// private List<CPU> cpu; // returns information about CPU characteristics
	private String mac; // returns the mac address
	private String ip; // returns the ip address
	private String hostName; // returns the hostname
	private String location; // returns the fake location
	private List<BundlerCommunication> listBundler = new ArrayList<BundlerCommunication>();;
	// private String[] intefaceNetwork; //returns a list with the existing
	// network interfaces
	private List<ServiceCommunication> listService;

	/* Describe information about devices connected to the gateway */
	private List<DeviceCommunication> listDevice = new ArrayList<DeviceCommunication>();

	private int flag;

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

	// public List<CPU> getCpu() {
	// return cpu;
	// }
	//
	// public void setCpu(List<CPU> cpu) {
	// this.cpu = cpu;
	// }

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<BundlerCommunication> getListBundler() {
		return listBundler;
	}

	public void setListBundler(List<BundlerCommunication> listBundler) {
		this.listBundler = listBundler;
	}

	// public String[] getIntefaceNetwork() {
	// return intefaceNetwork;
	// }
	//
	// public void setIntefaceNetwork(String[] intefaceNetwork) {
	// this.intefaceNetwork = intefaceNetwork;
	// }

	public List<ServiceCommunication> getListService() {
		return listService;
	}

	public void setListService(List<ServiceCommunication> listService) {
		this.listService = listService;
	}

	public List<DeviceCommunication> getListDevice() {
		return listDevice;
	}

	public void setListDevice(List<DeviceCommunication> listDevice) {
		this.listDevice = listDevice;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
