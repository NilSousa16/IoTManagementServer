package br.ufba.dcc.wiser.fot.manager.administration;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.communication.GatewayStatusCommunication;
import br.ufba.dcc.wiser.fot.manager.model.relationship.GatewayStatus;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;
import br.ufba.dcc.wiser.fot.manager.service.GatewayStatusDBService;

public class InformationGatewayStatus {

	/* Instance of GatewayDBService to retrieve and store information */
	private GatewayDBService gatewayDBService = null;
	
	/* Instance of GatewayStatusDBService to retrieve and store information */
	private GatewayStatusDBService gatewayStatusDBService = null;

	/* Method used by blueprint to create gatewayDBService instance */
	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}

	/* Method used by blueprint to create setGatewayStatusDBService instance */
	public void setGatewayStatusDBService(GatewayStatusDBService gatewayStatusDBService) {
		this.gatewayStatusDBService = gatewayStatusDBService;
	}

	@POST
	@Path("/addgatewaystatus")
	@Produces("application/json")
	public void addGatewayStatus(String value) {

		System.out.println("Information received in addGatewayStatus");
		System.out.println(value);

		Gson gson = new Gson();
		GatewayStatusCommunication gatewayStatusCommunication = new GatewayStatusCommunication();
		gatewayStatusCommunication = gson.fromJson(value, GatewayStatusCommunication.class);
		
		Gateway gatewayFind = new Gateway();
		gatewayFind = gatewayDBService.find(gatewayStatusCommunication.getMac());
		
		if (gatewayFind != null) {
			gatewayFind.setListBundlerInstalled(null);
			
			GatewayStatus gatewayStatus = new GatewayStatus();

			gatewayStatus.setGateway(gatewayFind);
			gatewayStatus.setBaterryLevel(gatewayStatusCommunication.getBaterryLevel());
			gatewayStatus.setFreeMemory(gatewayStatusCommunication.getFreeMemory());
			gatewayStatus.setFreeProcessor(gatewayStatusCommunication.getFreeProcessor());
			gatewayStatus.setTotalMemory(gatewayStatusCommunication.getTotalMemory());
			gatewayStatus.setUsedMemory(gatewayStatusCommunication.getUsedMemory());
			gatewayStatus.setUsedProcessor(gatewayStatusCommunication.getUsedProcessor());
			gatewayStatus.setDate(gatewayStatusCommunication.getDate());

			gatewayStatusDBService.add(gatewayStatus);

		} else {
			System.out.println(">>>Information GatewayStatus not saved.");
		}

	}

}
