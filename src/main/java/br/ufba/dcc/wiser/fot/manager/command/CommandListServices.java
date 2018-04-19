package br.ufba.dcc.wiser.fot.manager.command;

import java.util.List;

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalled;
import br.ufba.dcc.wiser.fot.manager.model.relationship.ServiceProvided;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;
import br.ufba.dcc.wiser.fot.manager.service.ServiceDBService;

/**
 * Class responsible for listing installed services at a given gateway.
 *
 * @author Nilson Rodrigues Sousa
 */
@Command(scope = "wiser", name = "list-services-installed", description = "List of all services installed in a given gateway")
public class CommandListServices implements Action {

	@Argument(index = 0, name = "mac", required = true, description = "Value mac", multiValued = false)
	private String mac;

	private ServiceDBService serviceDBService = null;

	public void setServiceDBService(ServiceDBService serviceDBService) {
		this.serviceDBService = serviceDBService;
	}

	/**
	 * A method that identifies the gateway through the MAC and returns its
	 * installed services.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param session
	 *            CommandSession
	 * @return Object - Reply required by karaf
	 */
	public Object execute(CommandSession session) throws Exception {

		List<ServiceProvided> listServiceBundlerUsed = serviceDBService.findBundlerUser(mac);

		System.out.println("\n---------------------------------------------------");
		System.out.println("REPORT SERVICE ACTIVE");
		System.out.println("---------------------------------------------------");
		if (listServiceBundlerUsed == null) {
			System.out.println("\nNo items stored.\n");
		} else {
			for (ServiceProvided lsp : listServiceBundlerUsed) {
				System.out.println("- ServiceProvided: " + lsp.getId().getService().getNameService());

				if (lsp.getBundlerUsed() != null && lsp.getBundlerUsed().isEmpty()) {
					System.out.println("\nNo bundles useds stored.\n");
				} else {
					for (BundlerInstalled bu : lsp.getBundlerUsed()) {
						System.out.println(" >>>>>>>>>>>BiUsed: " + bu.getId().getBundler().getName() + "Ver: " + bu.getId().getBundler().getVersion() );
					}
				}
			}
		}

		System.out.println("---------------------------------------------------\n");
		return null;
	}

}
