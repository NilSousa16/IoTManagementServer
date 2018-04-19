package br.ufba.dcc.wiser.fot.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.ufba.dcc.wiser.fot.manager.model.Device;
import br.ufba.dcc.wiser.fot.manager.service.DeviceDBService;

public class DeviceDBImpl implements DeviceDBService {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void addDevice(Device device) {
		try {
			entityManager.persist(device);
			entityManager.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Device findDevice(String name) {
		Device device = entityManager.find(Device.class, name);
		if (device != null) {
			return device;
		}
		return null;
	}

	public List<Device> listDevice() {
		try {
			List<Device> listDevice = new ArrayList<Device>();

			listDevice.addAll(entityManager.createQuery("select d from Device d", Device.class).getResultList());

			if (listDevice != null) {
				return listDevice;
			}
		} catch (Exception e) {
			System.out.println("Failed to list stored devices.");
			e.printStackTrace();
		}

		return null;
	}

}
