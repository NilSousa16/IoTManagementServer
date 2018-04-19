package br.ufba.dcc.wiser.fot.manager.command;

import java.util.List;

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

import br.ufba.dcc.wiser.fot.manager.model.Bundler;
import br.ufba.dcc.wiser.fot.manager.service.BundlerDBService;

/**
 * Class responsible for listing the bundles already stored in the database.
 *
 * @author Nilson Rodrigues Sousa
 */
@Command(scope = "wiser", name = "list-bundler-registered", description = "Lists the bundles that had data stored")
public class CommandListBundlerRegistered implements Action {

	private BundlerDBService bundlerDBService = null;

	public void setBundlerDBService(BundlerDBService bundlerDBService) {
		this.bundlerDBService = bundlerDBService;
	}

	/**
	 * Method that lists the bundles that have registration within the database.
	 * 
	 * @author Nilson Rodrigues Sousa
	 * @param session
	 *            CommandSession
	 * @return Object - Reply required by karaf
	 */
	public Object execute(CommandSession session) throws Exception {
		try {
			List<Bundler> listBundler = bundlerDBService.getListBundler();

			System.out.println("\n---------------------------------------------------");
			System.out.println("REPORT REGISTERED BUNDLERS");
			System.out.println("---------------------------------------------------");
			if (listBundler != null && !listBundler.isEmpty()) {
				for (Bundler bundler : listBundler) {
					System.out.println(">>> Bundler Name: " + bundler.getName());
					System.out.println(">>> Bundler Version: " + bundler.getVersion());
					System.out.println(">>> Bundler Location:" + bundler.getLocation());
				}
			} else {
				System.out.println("\nNo items stored.\n");
			}
			System.out.println("---------------------------------------------------");

			System.out.println("Operation successfully completed.");

		} catch (Exception e) {
			System.out.println("CommandListBundlerRegistered operation failed");
			e.printStackTrace();
		}

		return null;
	}

}
