package br.ufba.dcc.wiser.fot.manager.service;

import java.util.List;

import br.ufba.dcc.wiser.fot.manager.model.Device;

public interface DeviceDBService {
	
	public void addDevice(Device device);
	public Device findDevice(String name);
	public List<Device> listDevice();
	
}
