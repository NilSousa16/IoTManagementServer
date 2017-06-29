package br.ufba.dcc.wiser.fot.manager.administration;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

public class InformationGatewayStatus {

	// assing in the blueprint
	private GatewayDBService gatewayDBService = null;

	// Used by blueprint
	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}

	@POST
	@Path("/addgatewaystatus")
	@Produces("application/json")
	public void addGatewayStatus(String value) {
		
		System.out.println("Information received in addGatewayStatus");
		System.out.println(value);
		
//		Gson gson = new Gson();
//		Gateway gateway = gson.fromJson(value, Gateway.class);


	}

}
