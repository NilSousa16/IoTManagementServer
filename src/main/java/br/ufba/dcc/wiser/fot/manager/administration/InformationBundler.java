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

	/* Instance of GatewayDBService to retrieve and store information */
	private GatewayDBService gatewayDBService = null;
	
	/* BundlerDBService instance for storing information */
	private BundlerDBService bundlerDBService = null;

	/* Method used by blueprint to create gatewayDBService instance */
	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}

	/* Method used by blueprint to create bundlerDBService instance */
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
		GatewayCommunication gatewayCommunication = new GatewayCommunication();
		gatewayCommunication = gson.fromJson(value, GatewayCommunication.class);

		Gateway gatewayFind = new Gateway();
		gatewayFind = gatewayDBService.find(gatewayCommunication.getMac());

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

				bundlerDBService.addBundlerInstalled(bundlerDB, gatewayFind, bc.getStatus());

			}
			System.out.println("Bundlers stored successfully.");
		}
	}

	@POST
	@Path("/disconnectedbundler")
	@Produces("application/json")
	public void removeBundler(String value) {
		System.out.println("Information received in disconnectedbundler");
		System.out.println(value);

		Gson gson = new Gson();
		GatewayCommunication gatewayCommunication = gson.fromJson(value, GatewayCommunication.class);

		Gateway gatewayFind = gatewayDBService.find(gatewayCommunication.getMac());

		for (BundlerCommunication bc : gatewayCommunication.getListBundler()) {

			Bundler bundlerDB = bundlerDBService.find(bc.getName(), bc.getVersion());

			BundlerInstalled bundlerInstalled = bundlerDBService.findBundlerInstalled(bundlerDB, gatewayFind);
			System.out.println(">>>>bundlerInstalled find: " + bundlerInstalled.getId().getGateway().getMac());
			System.out.println(">>>>bundlerInstalled find: " + bundlerInstalled.getId().getBundler().getName());
			bundlerDBService.removeBundlerInstalled(bundlerInstalled);

		}
		System.out.println("Bundlers successfully disconnected.");
	}

	@POST
	@Path("/alteredbundler")
	@Produces("application/json")
	public void alterBundlerInformation(String value) {
		System.out.println("Information received in alterBundlerInformation");
		System.out.println(value);

		Gson gson = new Gson();
		GatewayCommunication gatewayCommunication = gson.fromJson(value, GatewayCommunication.class);

		Gateway gatewayFind = gatewayDBService.find(gatewayCommunication.getMac());

		for (BundlerCommunication bc : gatewayCommunication.getListBundler()) {

			Bundler bundlerDB = bundlerDBService.find(bc.getName(), bc.getVersion());

			BundlerInstalled bundlerInstalled = bundlerDBService.findBundlerInstalled(bundlerDB, gatewayFind);

			if (!(bundlerInstalled.getStatus().equals(bc.getStatus()))) {
				bundlerInstalled.setStatus(bc.getStatus());
			} 

			bundlerDBService.updateBundlerInstalled(bundlerInstalled);

		}

	}

}
