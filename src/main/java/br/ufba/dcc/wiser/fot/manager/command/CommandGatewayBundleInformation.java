package br.ufba.dcc.wiser.fot.manager.command;

import java.util.List;

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalled;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

@Command(scope = "wiser", name = "report-bundler-active")
public class CommandGatewayBundleInformation implements Action {

	private GatewayDBService gatewayDBService = null;

	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}

	public Object execute(CommandSession session) throws Exception {
		List<Gateway> listGateway = gatewayDBService.getListGateway();

		System.out.println("\n---------------------------------------------------");
		System.out.println("REPORT BUNDLES ACTIVE");
		System.out.println("---------------------------------------------------");
		if (listGateway.isEmpty()) {
			System.out.println("\nNo items stored.\n");
		}
		for (Gateway gateway : listGateway) {
			if (gateway.isStatus()) {

				List<BundlerInstalled> listBundler = gateway.getBundlerInstalled();

				System.out.println("\nGatewayIP: " + gateway.getIp());
				System.out.println("GatewayMAC: " + gateway.getMac());

				if (!listBundler.isEmpty() || listBundler == null) {
					for (BundlerInstalled bundlerInstalled : listBundler) {

						System.out.println(bundlerInstalled.getId().getBundler().getName());
						System.out.println(bundlerInstalled.getId().getBundler().getLocation());
						System.out.println(bundlerInstalled.getId().getBundler().getVersion());
						System.out.println(bundlerInstalled.isStatus());
					}
				}
			}
		}
		System.out.println("---------------------------------------------------\n");
		return null;
	}

}
