package br.ufba.dcc.wiser.fot.manager.administration;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

public class InformationGateway {

	//assing in the blueprint
	private GatewayDBService gatewayDBService = null;

	// Used by blueprint
	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	} 

	@POST
	@Path("/addgateway")
	@Produces("application/json")
	public void addGatewayInformation(String value) {
		
		System.out.println("Ok - Information received in addGateway");
		System.out.println(value);

		Gson gson = new Gson();
		Gateway gateway = gson.fromJson(value, Gateway.class);
		
		Gateway gatewayFind = gatewayDBService.find(gateway.getMac());
		
		//o gateway precisa esta setado como true no banco para se receber informações
		//possivel melhora com a adição do recurso de descoberta do gateway e atualização
		//ao inves de atualizar apenas as informações
		if(gatewayFind.isStatus() == true) {
			if(gateway.getDescription() != null && gateway.getDescription() != "") {
				gatewayFind.setDescription(gateway.getDescription());
			}
			
			if(gateway.getFirmware() != null && gateway.getFirmware() != "") {
				gatewayFind.setDescription(gateway.getDescription());
			}
			
			if(gateway.getHostName() != null && gateway.getHostName() != "") {
				gatewayFind.setHostName(gateway.getDescription());
			}
			
			if(gateway.getIp() != null && gateway.getIp() != "") {
				gatewayFind.setIp(gateway.getIp());
			}
			
			if(gateway.getLastUpdate() != null && gateway.getLastUpdate() != "") {
				gatewayFind.setLastUpdate(gateway.getLastUpdate());
			}
			
			if(gateway.getMac() != null && gateway.getMac() != "") {
				gatewayFind.setMac(gateway.getMac());
			}
			
			if(gateway.getManufacturer() != null && gateway.getManufacturer() != "") {
				gatewayFind.setManufacturer(gateway.getManufacturer());
			}
			
			if(gateway.getModel() != null && gateway.getModel() != "") {
				gatewayFind.setModel(gateway.getModel());
			}
			
			if(value.contains("true") || value.contains("false")) {
				gatewayFind.setStatus(gateway.isStatus());
			}
			
			if(value.contains("Storage")) {
				gatewayFind.setStorage(gateway.getStorage());
			}
			
			gatewayDBService.update(gatewayFind);
		}
		
	}

	@POST
	@Path("/altergatewayinformation")
	@Produces("application/json")
	public Response alterGatewayInformation(String value) {
		
		System.out.println("Ok - Information received in alterGatewayInformation");
		System.out.println(value);

		// System.out.println(">>>>>>>> " + value);
		// if (HashDB.getBook(value) == null) {
		// return Response.status(Response.Status.BAD_REQUEST).build();
		// } else {
		// return Response.ok(value).build();
		// }

		return null;

	}

}
