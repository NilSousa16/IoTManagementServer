package br.ufba.dcc.wiser.fot.manager.administration;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.ufba.dcc.wiser.fot.manager.intervention.RecoverIntervention;
import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.communication.GatewayCommunication;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

public class InformationGateway {

	/* Instance of GatewayDBService to retrieve and store information */
	private GatewayDBService gatewayDBService = null;
	
	/* Instance of RecoverIntervention to configure recovery */
	private RecoverIntervention recoverIntervention = null;

	/* Method used by blueprint to create gatewayDBService instance */
	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}

	/* Method used by blueprint to create recoverIntervention instance */
	public void setRecoverIntervention(RecoverIntervention recoverIntervention) {
		this.recoverIntervention = recoverIntervention;
	}

	@POST
	@Path("/addgateway")
	@Produces("application/json")
	public Response addGatewayInformation(String value) {
//	public void addGatewayInformation(String value) {

		System.out.println("Ok - Information received in addGateway");
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

		// If the gateway does not exist in the database
		if (gatewayFind == null) {
			System.out.println(">>>>>>>>>>>>>>>Entroy no ponto 01");
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
			// add location

			gatewayDBService.add(gateway);

			try {
				String response = URLDecoder.decode("true", "UTF-8");
				System.out.println(">>>>>>>>>>>>>>>>>>Add gateway ok");
				return Response.ok(response).build();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace(); // To change body of catch statement use
										// File |
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

			/* restore gateway configuration */
			System.out.println(">>>>>>>>>>Valor do flag: " + gatewayCommunication.getFlag());
			if (gatewayCommunication.getFlag() == 1) {
				System.out.println(">>>>>>>>>>>>>Realizar recuperação");
				
				System.out.println(">>>>>>>>>>>>>MAC para recuperação: " + gatewayCommunication.getMac());
				System.out.println(">>>>>>>>>>>>>MAC para recuperação2: " );
				System.out.println(">>>>>>>>>>>>>IP para recuperação: " + gatewayCommunication.getIp());
				recoverIntervention.sendRestoreConfiguration(gatewayCommunication.getMac());
			}

			System.out.println(">>>>>>>>>>>>>>>>>>Update in add gateway ok");
		}
		System.out.println(">>>>>>>>>>>>>>>Finalizou o metodo addGatewayInformation");
		
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
