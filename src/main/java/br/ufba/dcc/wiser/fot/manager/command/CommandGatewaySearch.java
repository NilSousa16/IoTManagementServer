package br.ufba.dcc.wiser.fot.manager.command;

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

/**
 * Class responsible for returning information from a specific gateway.
 *
 * @author Nilson Rodrigues Sousa
 */
@Command(scope = "wiser", name = "gateway-search", description = "Search gateways")
public class CommandGatewaySearch implements Action {

	@Argument(index = 0, name = "Mac", required = true, description = "Value mac", multiValued = false)
	String mac;

	private GatewayDBService gatewayDBService = null;

	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}

	/**
	 * Method responsible for returning the information of a specific gateway
	 * through the MAC address.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param session
	 *            CommandSession
	 * @return Object - Reply required by karaf
	 */
	public Object execute(CommandSession session) throws Exception {
		try {
			Gateway gateway = gatewayDBService.find(mac);

			System.out.println("\n---------------------------------------------------");
			System.out.println("REPORT REGISTERED GATEWAYS FULL");
			System.out.println("---------------------------------------------------");

			if (gateway != null) {
				System.out.println(">>>>> Mac: " + gateway.getMac());
				System.out.println("	Description: " + gateway.getDescription());
				System.out.println("	Model: " + gateway.getModel());
				System.out.println("	Manufacturer: " + gateway.getManufacturer());
				System.out.println("	Firmware: " + gateway.getFirmware());
				System.out.println("	Status: " + gateway.isStatus());
				System.out.println("	LastUpdate: " + gateway.getLastUpdate());
				System.out.println("	Ip: " + gateway.getIp());
				System.out.println("	HostName: " + gateway.getHostName());
				System.out.println("	Location: " + gateway.getLocation());
			} else {
				System.out.println("\nNo items stored.\n");
			}

			System.out.println("---------------------------------------------------");

			System.out.println("Search successfully completed.");
		} catch (Exception e) {
			System.out.println("CommandGatewaySearch operation failed");
			e.printStackTrace();
		}

		return null;
	}
}
