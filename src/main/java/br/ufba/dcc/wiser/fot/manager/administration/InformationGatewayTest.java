package br.ufba.dcc.wiser.fot.manager.administration;

import com.google.gson.Gson;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.communication.GatewayCommunication;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

public class InformationGatewayTest {

	// assing in the blueprint
	private GatewayDBService gatewayDBService = null;

	// Used by blueprint
	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}

	public void demoFunction() {
		if (gatewayDBService == null) {
			System.out.println(">>>Method InformationGateway isn't with the populated properties.");

			Gateway gateway = new Gateway();

			gateway.setIp("000.000.000.000");
			gateway.setMac("00-00-00-00");
			gateway.setStatus(true);

			gatewayDBService.add(gateway);

			System.out.println(">>>Successfully Added");
		} else {
			System.out.println(">>>Method InformationGateway is with the populated properties.");
		}
	}

	// @POST
	// @Path("/addgateway")
	// @Produces("application/json")
	public void addGatewayInformation(String value) {

		System.out.println("Ok - Information received in addGateway");
		value = "{\"description\":\"Debian_Light\",\"model\":\"0.11.2\",\"manufacturer\":\"Raspberry\",\"firmware\":\"2.1.0\",\"storage\":10982362,\"lastUpdate\":\"05/07/2017 5:50 - PM\",\"mac\":\"08-00-27-31-A7-71\",\"ip\":\"192.168.1.102\",\"hostName\":\"RaspberryHostName\",\"location\":\"-12.9990189,-38.5140298\"}";

		Gson gson = new Gson();
		GatewayCommunication gatewayCommunication = gson.fromJson(value, GatewayCommunication.class);

		System.out.println("\n--------------------------------------");
		System.out.println("VERIFICATION LOCAL VARIABLES");
		System.out.println("--------------------------------------\n");
		System.out.println(">>>IP: " + gatewayCommunication.getIp() + " MAC: " + gatewayCommunication.getMac());

		if (gatewayDBService == null) {
			System.out.println(">>> GatewayDBService is null.");
		} else {
			System.out.println(">>> GatewayDBService is not null.");
		}
		System.out.println("\n--------------------------------------\n");

		Gateway gatewayFind = gatewayDBService.find(gatewayCommunication.getMac());

		// If the gateway does not exist in the database
		if (gatewayFind == null) {

			Gateway gateway = new Gateway();

			gateway.setIp(gatewayCommunication.getIp());
			gateway.setMac(gatewayCommunication.getMac());
			gateway.setStatus(true);

			gatewayDBService.add(gateway);

			System.out.println(">>>>>>>>>>>>>>>>>>Add gateway ok");
		} else {
			if (gatewayCommunication.getDescription() != null && gatewayCommunication.getDescription() != "") {
				gatewayFind.setDescription(gatewayCommunication.getDescription());
			}

			if (gatewayCommunication.getFirmware() != null && gatewayCommunication.getFirmware() != "") {
				gatewayFind.setDescription(gatewayCommunication.getDescription());
			}

			if (gatewayCommunication.getHostName() != null && gatewayCommunication.getHostName() != "") {
				gatewayFind.setHostName(gatewayCommunication.getDescription());
			}

			if (gatewayCommunication.getIp() != null && gatewayCommunication.getIp() != "") {
				gatewayFind.setIp(gatewayCommunication.getIp());
			}

			if (gatewayCommunication.getLastUpdate() != null) {
				gatewayFind.setLastUpdate(gatewayCommunication.getLastUpdate());
			}

			if (gatewayCommunication.getMac() != null && gatewayCommunication.getMac() != "") {
				gatewayFind.setMac(gatewayCommunication.getMac());
			}

			if (gatewayCommunication.getManufacturer() != null && gatewayCommunication.getManufacturer() != "") {
				gatewayFind.setManufacturer(gatewayCommunication.getManufacturer());
			}

			if (gatewayCommunication.getModel() != null && gatewayCommunication.getModel() != "") {
				gatewayFind.setModel(gatewayCommunication.getModel());
			}

			// if (value.contains("true") || value.contains("false")) {
			if (gatewayFind.isStatus() == false) {
				gatewayFind.setStatus(gatewayCommunication.isStatus());
			}

			if (value.contains("Storage")) {
				gatewayFind.setStorage(gatewayCommunication.getStorage());
			}

			gatewayDBService.update(gatewayFind);

			System.out.println(">>>>>>>>>>>>>>>>>>Update in add gateway ok");
		}
	}

	// @POST
	// @Path("/altergatewayinformation")
	// @Produces("application/json")
	public void alterGatewayInformation(String value) {

		System.out.println("Ok - Information received in alterGatewayInformation");
		System.out.println(value);

		Gson gson = new Gson();
		GatewayCommunication gatewayCommunication = gson.fromJson(value, GatewayCommunication.class);

		System.out.println("\n--------------------------------------");
		System.out.println("VERIFICATION LOCAL VARIABLES");
		System.out.println("--------------------------------------\n");
		System.out.println(">>>IP: " + gatewayCommunication.getIp() + " MAC: " + gatewayCommunication.getMac());

		if (gatewayDBService == null) {
			System.out.println(">>> GatewayDBService is null.");
		} else {
			System.out.println(">>> GatewayDBService is not null.");
		}
		System.out.println("\n--------------------------------------\n");

		Gateway gatewayFind = gatewayDBService.find(gatewayCommunication.getMac());

		if (gatewayCommunication.getDescription() != null && gatewayCommunication.getDescription() != "") {
			gatewayFind.setDescription(gatewayCommunication.getDescription());
		}

		if (gatewayCommunication.getFirmware() != null && gatewayCommunication.getFirmware() != "") {
			gatewayFind.setDescription(gatewayCommunication.getDescription());
		}

		if (gatewayCommunication.getHostName() != null && gatewayCommunication.getHostName() != "") {
			gatewayFind.setHostName(gatewayCommunication.getDescription());
		}

		if (gatewayCommunication.getIp() != null && gatewayCommunication.getIp() != "") {
			gatewayFind.setIp(gatewayCommunication.getIp());
		}

		if (gatewayCommunication.getLastUpdate() != null) {
			gatewayFind.setLastUpdate(gatewayCommunication.getLastUpdate());
		}

		if (gatewayCommunication.getMac() != null && gatewayCommunication.getMac() != "") {
			gatewayFind.setMac(gatewayCommunication.getMac());
		}

		if (gatewayCommunication.getManufacturer() != null && gatewayCommunication.getManufacturer() != "") {
			gatewayFind.setManufacturer(gatewayCommunication.getManufacturer());
		}

		if (gatewayCommunication.getModel() != null && gatewayCommunication.getModel() != "") {
			gatewayFind.setModel(gatewayCommunication.getModel());
		}

		// if (value.contains("true") || value.contains("false")) {
		if (gatewayFind.isStatus() == false) {
			gatewayFind.setStatus(gatewayCommunication.isStatus());
		}

		if (value.contains("Storage")) {
			gatewayFind.setStorage(gatewayCommunication.getStorage());
		}

		gatewayDBService.update(gatewayFind);

		System.out.println(">>>>>>>>>>>>>>>>>>Update gateway ok");

	}

}
