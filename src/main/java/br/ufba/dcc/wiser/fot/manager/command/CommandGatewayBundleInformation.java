package br.ufba.dcc.wiser.fot.manager.command;

import java.util.ArrayList;
import java.util.List;

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalled;
import br.ufba.dcc.wiser.fot.manager.service.BundlerDBService;

/**
 * Class responsible for returning bundles installed on all existing gateways.
 *
 * @author Nilson Rodrigues Sousa
 */
@Command(scope = "wiser", name = "gateway-bundler")
public class CommandGatewayBundleInformation implements Action {

	private BundlerDBService bundlerDBService = null;

	public void setBundlerDBService(BundlerDBService bundlerDBService) {
		this.bundlerDBService = bundlerDBService;
	}

	/**
	 * Method that returns the IP and MAC of the gateways with the bundles
	 * installed on them.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param session
	 *            CommandSession
	 * @return Object - Reply required by karaf
	 */
	public Object execute(CommandSession session) throws Exception {
		List<Gateway> listGateway = new ArrayList<Gateway>();

		listGateway = bundlerDBService.getListBundlersGateway();

		System.out.println("\n---------------------------------------------------");
		System.out.println("REPORT BUNDLES ACTIVE");
		System.out.println("---------------------------------------------------");
		if (listGateway.isEmpty()) {
			System.out.println("\nNo items stored.\n");
		} else {
			for (Gateway gateway : listGateway) {
				if (gateway.isStatus()) {
					System.out.println("P>>>>>>>>>>>>ENTROU");
					System.out.println("\nGatewayIP: " + gateway.getIp());
					System.out.println("GatewayMAC: " + gateway.getMac());
					System.out.println("VAI ENTRAR LAÇO");
					if (!gateway.getListBundlerInstalled().isEmpty()) {
						System.out.println("ESTAMOS NO LAÇO");
						for (BundlerInstalled bundlerInstalled : gateway.getListBundlerInstalled()) {
							System.out.println("Name:" + bundlerInstalled.getId().getBundler().getName());
							System.out.println("Location: " + bundlerInstalled.getId().getBundler().getLocation());
							System.out.println("Version: " + bundlerInstalled.getId().getBundler().getVersion());
							System.out.println("Status:" + bundlerInstalled.getStatus());
							System.out.println("\n----------------------------------\n");
						}
					}
				}
			}
		}
		System.out.println("---------------------------------------------------\n");
		return null;
	}

}
