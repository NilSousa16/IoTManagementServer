package br.ufba.dcc.wiser.fot.manager.command;

import java.util.List;

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

@Command(scope = "ws", name = "gatewayInformationSimple")
public class CommandGatewayInformationSimple implements Action {

	private GatewayDBService gatewayDBService = null;

	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}
	
	public Object execute(CommandSession session) throws Exception {
		List<Gateway> gateways = gatewayDBService.getListGateway();

		System.out.println("\n##########GATEWAY REPORT BD##########");
		for (Gateway gateway : gateways) {
			System.out.println(
					"Ip [" + gateway.getIp() + "] Mac [" + gateway.getMac() + "] Status [" + gateway.isStatus() + "]");
		}
		System.out.println("#####################################\n");
		return null;
	}

}
