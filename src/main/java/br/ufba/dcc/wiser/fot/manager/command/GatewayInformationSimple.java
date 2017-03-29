package br.ufba.dcc.wiser.fot.manager.command;

import java.util.List;

import org.apache.felix.service.command.CommandSession;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

public class GatewayInformationSimple {
	
	private GatewayDBService gatewayDBService = null;

	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}
	
	public Object execute(CommandSession session) throws Exception {
		List<Gateway> gateways = gatewayDBService.getListGateway();

		for (Gateway gateway : gateways) {
			System.out.println("\n################################\n");
			System.out.println(">>>>> Description: " + gateway.getDescription());
			System.out.println(">>>>> Status: " + gateway.isStatus());
			System.out.println(">>>>> LastUpdate: " + gateway.getLastUpdate());
			System.out.println(">>>>> TotalMemory: " + gateway.getTotalMemory());
			System.out.println(">>>>> UsedProcessor" + gateway.getUsedProcessor() + "%");
			System.out.println(">>>>> Mac: " + gateway.getMac());
			System.out.println(">>>>> Ip: " + gateway.getIp());
			System.out.println("\n################################\n");
		}
		
		return null;
	}

}
