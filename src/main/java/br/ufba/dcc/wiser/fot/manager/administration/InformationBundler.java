package br.ufba.dcc.wiser.fot.manager.administration;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import br.ufba.dcc.wiser.fot.manager.model.Bundler;
import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.communication.BundlerCommunication;
import br.ufba.dcc.wiser.fot.manager.model.communication.GatewayCommunication;
import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalled;
import br.ufba.dcc.wiser.fot.manager.service.BundlerDBService;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;

public class InformationBundler {

	// assing in the blueprint
	private GatewayDBService gatewayDBService = null;
	private BundlerDBService bundlerDBService = null;

	// Used by blueprint
	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}

	// Used by blueprint
	public void setBundlerDBService(BundlerDBService bundlerDBService) {
		this.bundlerDBService = bundlerDBService;
	}

	@POST
	@Path("/connectedbundler")
	@Produces("application/json")
	public void addBundler(String value) {

		System.out.println("Information received in addBundler");
		System.out.println(value);

		Gson gson = new Gson();
		GatewayCommunication gatewayCommunication = gson.fromJson(value, GatewayCommunication.class);
		System.out.println("Before gatewayDBService.find(gatewayCommunication.getMac())");
		Gateway gatewayFind = gatewayDBService.find(gatewayCommunication.getMac());
		System.out.println("After gatewayDBService.find(gatewayCommunication.getMac())");
		if (gatewayFind != null) {
			for (BundlerCommunication bc : gatewayCommunication.getListBundler()) {

				Bundler newBundler = new Bundler();
				Bundler bundlerDB = bundlerDBService.find(bc.getName(), bc.getVersion());

				if (bundlerDB == null) {
					newBundler.setName(bc.getName());
					newBundler.setLocation(bc.getLocation());
					newBundler.setVersion(bc.getVersion());

					bundlerDBService.add(newBundler);
					bundlerDB = bundlerDBService.find(bc.getName(), bc.getVersion());
				}

				bundlerDBService.addBundlerInstalled(bundlerDB, gatewayFind);
				System.out.println("Information stored successfully.");
			}
		}
	}

	@POST
	@Path("/disconnectedbundler")
	@Produces("application/json")
	public void removeBundler(String value) {
		System.out.println("Information received in disconnectedbundler");
		System.out.println(value);
		//criar bundle generico e realiza esse test.
		Gson gson = new Gson();
		GatewayCommunication gatewayCommunication = gson.fromJson(value, GatewayCommunication.class);

		Gateway gatewayFind = gatewayDBService.find(gatewayCommunication.getMac());

		for (BundlerCommunication bc : gatewayCommunication.getListBundler()) {

			Bundler bundlerDB = bundlerDBService.find(bc.getName(), bc.getVersion());

			BundlerInstalled bundlerInstalled = bundlerDBService.findBundlerInstalled(bundlerDB, gatewayFind);

			bundlerDBService.removeBundlerInstalled(bundlerInstalled);

		}

	}

	@POST
	@Path("/alteredbundler")
	@Produces("application/json")
	public void alterBundlerInformation(String value) {
		System.out.println("Information received in alterBundlerInformation");
		System.out.println(value);

		// Gson gson = new Gson();
		// GatewayCommunication gatewayCommunication = gson.fromJson(value,
		// GatewayCommunication.class);
		//
		// Gateway gatewayFind =
		// gatewayDBService.find(gatewayCommunication.getMac());
		//
		// for (BundlerCommunication bc : gatewayCommunication.getListBundler())
		// {
		//
		// Bundler bundlerDB = bundlerDBService.find(bc.getName(),
		// bc.getVersion());
		//
		// BundlerInstalled bundlerInstalled = bundlerDBService.
		// findBundlerInstalled(bundlerDB, gatewayFind);
		//
		// if(bundlerInstalled.isStatus() == true) {
		// bundlerInstalled.setStatus(false);
		// } else {
		// bundlerInstalled.setStatus(true);
		// }
		//
		// bundlerDBService.updateBundlerInstalled(bundlerInstalled);
		//
		// }

	}

}
