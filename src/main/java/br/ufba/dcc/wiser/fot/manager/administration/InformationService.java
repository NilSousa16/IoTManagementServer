package br.ufba.dcc.wiser.fot.manager.administration;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.ufba.dcc.wiser.fot.manager.model.Bundler;
import br.ufba.dcc.wiser.fot.manager.model.Gateway;
import br.ufba.dcc.wiser.fot.manager.model.Service;
import br.ufba.dcc.wiser.fot.manager.model.communication.BundlerCommunication;
import br.ufba.dcc.wiser.fot.manager.model.communication.GatewayCommunication;
import br.ufba.dcc.wiser.fot.manager.model.communication.ServiceCommunication;
import br.ufba.dcc.wiser.fot.manager.model.relationship.BundlerInstalled;
import br.ufba.dcc.wiser.fot.manager.model.relationship.ServiceProvided;
import br.ufba.dcc.wiser.fot.manager.service.BundlerDBService;
import br.ufba.dcc.wiser.fot.manager.service.GatewayDBService;
import br.ufba.dcc.wiser.fot.manager.service.ServiceDBService;

public class InformationService {

	/* Instance of serviceDBService to retrieve and store information */
	private ServiceDBService serviceDBService = null;

	/* Instance of bundlerDBService to retrieve and store information */
	private BundlerDBService bundlerDBService = null;

	/* Instance of GatewayDBService to retrieve and store information */
	private GatewayDBService gatewayDBService = null;

	/* Method used by blueprint to create serviceDBService instance */
	public void setServiceDBService(ServiceDBService serviceDBService) {
		this.serviceDBService = serviceDBService;
	}

	/* Method used by blueprint to create bundlerDBService instance */
	public void setBundlerDBService(BundlerDBService bundlerDBService) {
		this.bundlerDBService = bundlerDBService;
	}

	/* Method used by blueprint to create gatewayDBService instance */
	public void setGatewayDBService(GatewayDBService gatewayDBService) {
		this.gatewayDBService = gatewayDBService;
	}

	@POST
	@Path("/connectedservice")
	@Produces("application/json")
	public void addService(String value) {
		
		System.out.println("Information received in connectedService KKKKK");
		System.out.println(value);

		Gson gson = new Gson();

		GatewayCommunication gatewayCommunicationFind = gson.fromJson(value, GatewayCommunication.class);

		Gateway gatewayFind = gatewayDBService.find(gatewayCommunicationFind.getMac());

		List<ServiceCommunication> listServiceCommunication = gatewayCommunicationFind.getListService();

		for (ServiceCommunication sc : listServiceCommunication) {

			Service serviceFind = serviceDBService.find(sc.getNameService());

			if (serviceFind == null) {
				Service service = new Service();

				service.setNameService(sc.getNameService());

				serviceDBService.add(service);

				serviceFind = serviceDBService.find(sc.getNameService());
			}

			Bundler bundlerFind = bundlerDBService.find(sc.getBundlerProvide().getName(),
					sc.getBundlerProvide().getVersion());

			BundlerInstalled bundlerInstalled = bundlerDBService.findBundlerInstalled(bundlerFind, gatewayFind);

			ServiceProvided serviceProvidedFind = serviceDBService.findServiceProvided(bundlerInstalled, serviceFind);
			if (serviceProvidedFind != null) {
				continue;
			}

			serviceDBService.addServiceProvided(bundlerInstalled, serviceFind);

			/* Whereas bundlerInstalled always exists */
			/* Service_used storage */
			ServiceProvided serviceProvided = serviceDBService.findServiceProvided(bundlerInstalled, serviceFind);

			for (BundlerCommunication bc : sc.getListUsesBundles()) {
				Bundler bundlerFindUsed = bundlerDBService.find(bc.getName(), bc.getVersion());

				BundlerInstalled bundlerInstalledUse = bundlerDBService.findBundlerInstalled(bundlerFindUsed,
						gatewayFind);

				serviceDBService.addBundlerUser(bundlerInstalledUse, serviceProvided);
			}

		}
	}

	@POST
	@Path("/disconnectedservice")
	@Produces("application/json")
	public void disconnectedService(String value) {

		System.out.println("Information received in disconnectedService KKKKK");
		System.out.println(value);

		// COMEÇAR IMPLEMENTANDO ESSE AQUI - 25/08
		// REMOÇAO TOTAL CASCATE DEIXANDO OS SERVICE (TALVEZ NÃO SEJA CASCATE)

		// serviço q foi removido talvez tenha q verificar a deleção cascate

		Gson gson = new Gson();

		GatewayCommunication gatewayCommunicationFind = gson.fromJson(value, GatewayCommunication.class);

		Gateway gatewayFind = gatewayDBService.find(gatewayCommunicationFind.getMac());

		List<ServiceCommunication> listServiceDisconnected = gatewayCommunicationFind.getListService();

		for (ServiceCommunication sc : listServiceDisconnected) {

			Bundler bundlerFind = bundlerDBService.find(sc.getBundlerProvide().getName(),
					sc.getBundlerProvide().getVersion());

			BundlerInstalled bundlerInstalled = bundlerDBService.findBundlerInstalled(bundlerFind, gatewayFind);

			Service serviceFind = serviceDBService.find(sc.getNameService());

			serviceDBService.removeServiceProvided(bundlerInstalled, serviceFind);

		}
	}

	@POST
	@Path("/alteredserviceconnectedbundleruse")
	@Produces("application/json")
	public Response alteredServiceConnectedBundlerUse(String value) {

		System.out.println("Information received in alteredServiceConnectedBundlerUse");
		System.out.println(value);
		
		Gson gson = new Gson();

		GatewayCommunication gatewayCommunicationFind = gson.fromJson(value, GatewayCommunication.class);

		Gateway gatewayFind = gatewayDBService.find(gatewayCommunicationFind.getMac());

		List<ServiceCommunication> listServiceConnected = gatewayCommunicationFind.getListService();

		/* scrolls service list with new bundlersUser */
		for (ServiceCommunication sc : listServiceConnected) {

			Bundler bundlerFind = bundlerDBService.find(sc.getBundlerProvide().getName(),
					sc.getBundlerProvide().getVersion());

			BundlerInstalled bundlerInstalled = bundlerDBService.findBundlerInstalled(bundlerFind, gatewayFind);

			Service serviceFind = serviceDBService.find(sc.getNameService());
			
			/* search in the DB the serviceProvided that will be added new bundlersUser */
			ServiceProvided serviceProvided = serviceDBService.findServiceProvided(bundlerInstalled, serviceFind);

			/* scrolls list of new bundlersUser of a service */
			for (BundlerCommunication bc : sc.getListUsesBundles()) {
				/* search bundler version */
				Bundler bundlerFindUsed = bundlerDBService.find(bc.getName(), bc.getVersion());

				/* search BundlerInstalled to store BundlerUser */
				BundlerInstalled bundlerInstalledUse = bundlerDBService.findBundlerInstalled(bundlerFindUsed,
						gatewayFind);

				/* add new BundlerUser in serviceProvided */
				serviceDBService.addBundlerUser(bundlerInstalledUse, serviceProvided);
			}

		}
		
		return null;
	}

	@POST
	@Path("/alteredservicedisconnectedbundleruse")
	@Produces("application/json")
	public Response alteredServiceDisconnectedBundlerUse(String value) {

		System.out.println("Information received in alteredServiceDisconnectedBundlerUse");
		System.out.println(value);
		
		Gson gson = new Gson();

		GatewayCommunication gatewayCommunicationFind = gson.fromJson(value, GatewayCommunication.class);

		Gateway gatewayFind = gatewayDBService.find(gatewayCommunicationFind.getMac());

		List<ServiceCommunication> listServiceDisconnected = gatewayCommunicationFind.getListService();

		/* scrolls through the list of services with bundlerUse to be disconnected */
		for (ServiceCommunication sc : listServiceDisconnected) {

			Bundler bundlerFind = bundlerDBService.find(sc.getBundlerProvide().getName(),
					sc.getBundlerProvide().getVersion());

			BundlerInstalled bundlerInstalled = bundlerDBService.findBundlerInstalled(bundlerFind, gatewayFind);

			Service serviceFind = serviceDBService.find(sc.getNameService());
			
			/* search in the DB the serviceProvided that will be removed bundlersUser */
			ServiceProvided serviceProvided = serviceDBService.findServiceProvided(bundlerInstalled, serviceFind);

			/* scrolls list of remove bundlersUser of a service */
			for (BundlerCommunication bc : sc.getListUsesBundles()) {
				/* search bundler version */
				Bundler bundlerFindUsed = bundlerDBService.find(bc.getName(), bc.getVersion());

				/* search BundlerInstalled to remove BundlerUser */
				BundlerInstalled bundlerInstalledUse = bundlerDBService.findBundlerInstalled(bundlerFindUsed,
						gatewayFind);

				/* remove BundlerUser in serviceProvided */
				serviceDBService.removeBundlerUser(bundlerInstalledUse, serviceProvided);
			}

		}
		
		return null;
	}

}
