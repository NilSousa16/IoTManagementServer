package br.ufba.dcc.wiser.fot.manager.command;

import javax.persistence.EntityManager;

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalled;
import br.ufba.dcc.wiser.fot.manager.service.BundlerDBService;

/**
 * Class responsible for returning bundle information installed at a given
 * gateway.
 *
 * @author Nilson Rodrigues Sousa
 */
@Command(scope = "wiser", name = "list-bundler-installed", description = "List of all bundles installed in a given gateway")
public class CommandListBundlerInstalled implements Action {

	@Argument(index = 0, name = "mac", required = true, description = "Value mac", multiValued = false)
	private String mac;

	private EntityManager entityManager = null;

	private BundlerDBService bundlerDBService = null;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void setBundlerDBService(BundlerDBService bundlerDBService) {
		this.bundlerDBService = bundlerDBService;
	}

	/**
	 * Method that uses the MAC address to identify the gateway that should be
	 * returned to the list of installed bundles.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param session
	 *            CommandSession
	 * @return Object - Reply required by karaf
	 */
	public Object execute(CommandSession session) throws Exception {

		Gateway gateway = bundlerDBService.listBundlerInstalled(mac);

		System.out.println("\n---------------------------------------------------");
		System.out.println("REPORT BUNDLERS INSTALLED");
		System.out.println("---------------------------------------------------");
		if (gateway != null) {
			for (BundlerInstalled bundler : gateway.getListBundlerInstalled()) {
				System.out.println(">>> BundlerName: " + bundler.getId().getBundler().getName());
				System.out.println(">>> BundlerVersion: " + bundler.getId().getBundler().getVersion());
				System.out.println(">>> BundlerLocation:" + bundler.getId().getBundler().getLocation());
				System.out.println(">>> BundlerStatus:" + bundler.getStatus());
				System.out.println("###################################################\n");
			}
		} else {
			System.out.println("\nNo items stored.\n");
		}
		System.out.println("---------------------------------------------------");

		System.out.println("Operation successfully completed.");

		return null;
	}

}
