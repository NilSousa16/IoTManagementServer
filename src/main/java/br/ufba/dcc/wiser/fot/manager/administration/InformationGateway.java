package br.ufba.dcc.wiser.fot.manager.administration;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.communication.GatewayCommunication;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

public class InformationGateway {

	// assing in the blueprint
	private GatewayDBService gatewayDBService = null;

	// Used by blueprint
	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}
	
	@POST
	@Path("/addgateway")
	@Produces("application/json")
	public Response addGatewayInformation(String value) {

		System.out.println("Ok - Information received in addGateway");
		System.out.println(value);
		
		Gson gson = new Gson();
		GatewayCommunication gatewayCommunication = gson.fromJson(value, GatewayCommunication.class);

		System.out.println("\n--------------------------------------");
		System.out.println("VERIFICATION LOCAL VARIABLES");
		System.out.println("--------------------------------------\n");
		System.out.println(">>>IP: " + gatewayCommunication.getIp() + " MAC: " + gatewayCommunication.getMac());
		
		if(gatewayDBService == null) {
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
			gateway.setDescription(gatewayCommunication.getDescription());
			gateway.setFirmware(gatewayCommunication.getFirmware());
			gateway.setHostName(gatewayCommunication.getHostName());
			gateway.setManufacturer(gatewayCommunication.getManufacturer());
			gateway.setModel(gatewayCommunication.getModel());
			gateway.setStorage(gatewayCommunication.getStorage());
			gateway.setLastUpdate(gatewayCommunication.getLastUpdate());
			//add location
			
			gatewayDBService.add(gateway);
			
			try {
				String response = URLDecoder.decode("true", "UTF-8");
				System.out.println(">>>>>>>>>>>>>>>>>>Add gateway ok");
				return Response.ok(response).build();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace(); // To change body of catch statement use File |
										// Settings | File Templates.
			}
			
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
		
		return null;
		
	}

	@POST
	@Path("/altergatewayinformation")
	@Produces("application/json")
	public void alterGatewayInformation(String value) {

		System.out.println("Ok - Information received in alterGatewayInformation");
		System.out.println(value);

		Gson gson = new Gson();
		GatewayCommunication gatewayCommunication = gson.fromJson(value, GatewayCommunication.class);
		
		System.out.println("\n--------------------------------------");
		System.out.println("VERIFICATION LOCAL VARIABLES");
		System.out.println("--------------------------------------\n");
		System.out.println(">>>IP: " + gatewayCommunication.getIp() + " MAC: " + gatewayCommunication.getMac());
		
		if(gatewayDBService == null) {
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
