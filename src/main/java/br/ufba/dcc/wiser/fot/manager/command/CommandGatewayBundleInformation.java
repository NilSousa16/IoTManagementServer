package br.ufba.dcc.wiser.fot.manager.command;

import java.util.List;

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

@Command(scope = "ws", name = "gatewayBundleInformationSimple")
public class CommandGatewayBundleInformation implements Action {

	private GatewayDBService gatewayDBService = null;

	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}

	public Object execute(CommandSession session) throws Exception {
		List<Gateway> gateways = gatewayDBService.getListGateway();

		System.out.println("\n##########GATEWAY REPORT BD##########");
		for (Gateway gateway : gateways) {
			if (gateway.isStatus()) {

				//List<Bundler> list = gateway.getListBundler();

				System.out.println("\n>>>>>> GatewayIP: " + gateway.getIp());

				//for (Bundler bundle : list) {
				//	System.out.println(" - Name: " + bundle.getName() + " Version: " + bundle.getVersion()
				//			+ " Location: " + bundle.getLocation());
				//}
			}
		}
		System.out.println("#####################################\n");
		return null;
	}

}
