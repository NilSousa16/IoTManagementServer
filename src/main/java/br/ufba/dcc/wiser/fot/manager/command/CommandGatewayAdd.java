package br.ufba.dcc.wiser.fot.manager.command;

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

/**
 * Class for manually storing gateway with ip, mac, and status in the database.
 *
 * @author Nilson Rodrigues Sousa
 */
@Command(scope = "wiser", name = "add-gateway", description = "Adds a gateways")
public class CommandGatewayAdd implements Action {

	@Argument(index = 0, name = "Ip", required = true, description = "Value ip", multiValued = false)
	String ip;

	@Argument(index = 1, name = "Mac", required = true, description = "Value mac", multiValued = false)
	String mac;

	private GatewayDBService gatewayDBService = null;

	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}

	/**
	 * Method for storing a gateway passing as mac and ip parameters, and the
	 * status true will be assigned.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param session
	 *            CommandSession
	 * @return Object - Reply required by karaf
	 */
	public Object execute(CommandSession session) throws Exception {

		try {
			Gateway gateway = new Gateway();
			gateway.setMac(mac);
			gateway.setIp(ip);
			gateway.setStatus(true);

			gatewayDBService.add(gateway);

			System.out.println("\nInsertion successfully completed.");
		} catch (Exception e) {
			System.out.println("\nStorage operation failed");
			e.printStackTrace();
		}

		return null;
	}
}
