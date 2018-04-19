package br.ufba.dcc.wiser.fot.manager.model.communication;

import java.util.ArrayList;
import java.util.List;

/**
* Model for capturing the information received from the gateway.
*
* @author Nilson Rodrigues Sousa
*/
public class DeviceCommunication {

	/* Describes the name of the device */
	private String name;

	/* Describes the manufacturer of the device */
	private String manufacturer;

	/* Describes the version of the device */
	private String version;

	/* Describes the location of the device */
	private String location;

	/* Describes the URI of the device */
	private String descriptionSemantic;

	/* Device sensor list */
	private List<SensorCommunication> listSensor = new ArrayList<SensorCommunication>();

	/* Device actuador list */
	private List<ActuatorCommunication> listActuator = new ArrayList<ActuatorCommunication>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescriptionSemantic() {
		return descriptionSemantic;
	}

	public void setDescriptionSemantic(String descriptionSemantic) {
		this.descriptionSemantic = descriptionSemantic;
	}

	public List<SensorCommunication> getListSensor() {
		return listSensor;
	}

	public void setListSensor(List<SensorCommunication> listSensor) {
		this.listSensor = listSensor;
	}

	public List<ActuatorCommunication> getListActuator() {
		return listActuator;
	}

	public void setListActuator(List<ActuatorCommunication> listActuador) {
		this.listActuator = listActuador;
	}

}
